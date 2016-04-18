package interview.snapchat;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/*
 * Google Guava Rate Limiter: https://github.com/google/guava/blob/v18.0/guava/src/com/google/common/util/concurrent/RateLimiter.java
 * Ask: 是否允许「前人挖坑后人跳」，也就是说 RateLimiter 允许某次请求拿走超出剩余令牌数的令牌，但是下一次请求将为此付出代价，一直等到令牌亏空补上，并且桶中有足够本次请求使用的令牌为止[2]。这里面就涉及到一个权衡，
 * 是让前一次请求干等到令牌够用才走掉呢，还是让它先走掉后面的请求等一等呢？Guava 的设计者选择的是后者，先把眼前的活干了，后面的事后面再说。
 * 
 * 一个用DelayQueue实现的版本http://blog.nirav.name/2015/02/a-simple-rate-limiter-using-javas.html
 * 参考 http://www.cnblogs.com/LBSer/p/4083131.html
 * 
 */
public class RateLimitMy {
	public static void main(String[] args) {
		//TODO 等待测试！！！
	}
}


/*
 * 原po主做法：Deque + Leaky bucket as a meter (equivalent to token buckets)
 * 用Deque，每次进来一个access就check size是否超过k,如果超了检查deque最前面的时间，
 * 如果和当前时间之差超过1s那就pop最前面的一个，把当前的时间放进去return true，如果没超过1s那就return false
 * 缺点: 1. ArrayDeque不支持并发
 * 2. Leaky bucket
 */
class Throttler {
    int qps; // request(permit) per second,同时也是每秒会有 r 个令牌放入桶中
    
    int storePermitLimit; //我自己添加的数据结构，桶中最多存放b个令牌。
    
    Deque<Long> bucket; //我自己添加的数据结构，令牌桶
    
    public Throttler(int qps) {
    	this.qps = qps;
    	storePermitLimit = 5;
    	bucket = new ArrayDeque<Long>(storePermitLimit);
    }
    
    public boolean allowAccess() { // 这个函数就是acquire()
    	long time = System.currentTimeMillis();
    	if (bucket.size() < storePermitLimit || (time - bucket.peekFirst()) > 1000){
            if (bucket.size() > storePermitLimit) {
            	bucket.pollFirst();
            }
            bucket.offer(time);
            return true;
         } else {
            return false;
         }
    }
}

/*
 * 最优做法！
 * 我的做法：用LinkedBlockingDeque，仍旧默认按照收到请求的先来后到
 * 代价：DelayQueue本质是PriorityQueue,每次offer是log(n)的代价，不是contant代价
 * 用Deque，每次进来一个access就check size是否超过k,如果超了检查deque最前面的时间，
 * 如果和当前时间之差超过1s那就pop最前面的一个，把当前的时间放进去return true，如果没超过1s那就return false
 */
class Throttler2 {
    int qps; // request(permit) per second,同时也是每秒会有 r 个令牌放入桶中
    Deque<Long> bucket; //我自己添加的数据结构，令牌桶
    
    public Throttler2(int qps) {
    	this.qps = qps;
    	bucket = new LinkedBlockingDeque<Long>(qps);
    }
    
    public boolean allowAccess() {
    	long time = System.nanoTime();
    	if (bucket.size() < qps || (time - bucket.peekFirst()) > 1000000) {
            if (bucket.size() > qps) {
            	bucket.pollFirst();
            }
            bucket.offerLast(time);
            return true;
         } else {
            return false;
         }
    }
}

/*
 * 我的做法：用DelayQueue，可以根据请求的过期时间或者优先级来排序谁先放弃，同时从原来kilo-level qps -> mega-level qps
 * 代价：DelayQueue本质是PriorityQueue,每次offer是log(n)的代价，不是contant代价
 * 用Deque，每次进来一个access就check size是否超过k,如果超了检查deque最前面的时间，
 * 如果和当前时间之差超过1s那就pop最前面的一个，把当前的时间放进去return true，如果没超过1s那就return false
 */
class Throttler3 {
    int qps; // request(permit) per second,同时也是每秒会有 r 个令牌放入令牌桶中,也即漏桶中最多存放这么多个permit
    
    BlockingQueue<DelayedEntry> bucket; //我自己添加的数据结构，令牌桶
    
    public Throttler3(int qps) {
    	this.qps = qps;
    	bucket = new DelayQueue<DelayedEntry>();
    }
    
    public boolean allowAccess() {
    	DelayedEntry entry = new DelayedEntry();
    	if (bucket.size() < qps || entry.compareByNano(bucket.peek()) > 1000000){
            if (bucket.size() >= qps) {
            	bucket.poll();
            }
            bucket.offer(entry);
            return true;
         } else {
            return false;
         }
    }
}

class DelayedEntry implements Delayed {
	long expireAt; // nano
	TimeUnit unit;
	
	DelayedEntry() {
		this.expireAt = System.nanoTime();
		unit = TimeUnit.NANOSECONDS;
	}
	
	DelayedEntry(long delay, TimeUnit tu) {
		unit = tu;
		setDelay(delay);
	}
	
	public long compareByNano(Delayed other) {
		return this.expireAt - other.getDelay(unit);
	}

	void setDelay(long delay) {
		this.expireAt = System.nanoTime() + unit.toNanos(delay);
	}
	  
	public long getDelay(TimeUnit u) {
		return u.convert(expireAt - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		return (int) compareByNano(o);
	}
}


//在 Wikipedia 上，令牌桶算法是这么描述的：
//
//每秒会有 r 个令牌放入桶中，或者说，每过 1/r 秒桶中增加一个令牌
//桶中最多存放 b 个令牌，如果桶满了，新放入的令牌会被丢弃
//当一个 n 字节的数据包到达时，消耗 n 个令牌，然后发送该数据包
//如果桶中可用令牌小于 n，则该数据包将被缓存或丢弃

//Inefficiency of the leaky-bucket as a queue implementation[edit]
//The implementation of the leaky-bucket as a queue does not use available network resources efficiently. 
//Because it transmits packets only at fixed intervals, 
//there will be many instances when the traffic volume is very low 
//and large portions of network resources (bandwidth in particular) are not being used. 
//Therefore no mechanism exists in the leaky-bucket implementation as a queue to allow individual flows to burst up to port speed, 
//effectively consuming network resources at times when there would not be resource contention in the network. 
//Implementations of the token bucket and leaky bucket as a meter do, however, allow output traffic flows to have bursty characteristics.