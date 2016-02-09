package algorithm;

import java.util.LinkedList;

public class SimplifyPath {
	public static String simplifyPath(String path) {
		if (path == null || path.isEmpty() || path.equals("/")) { return "/"; }
		LinkedList<String> queue = new LinkedList<String>();
		String[] splits = path.split("\\/");  
		StringBuilder sb = new StringBuilder("/");
		int i, len;
		// 开头所有斜杠都会当成空，每个都占数组中的单独一位
		for (i = 0; i < splits.length && !splits[i].isEmpty(); i++) {}
		
		// 遍历
		for (; i < splits.length; i++) {
			if (splits[i].isEmpty() || splits[i].equals(".")) { continue; }
			if (splits[i].equals("..")) {
				if (!queue.isEmpty()) { queue.removeLast(); } // 这里不能用poll，因为LinkedList的poll是从前删除
				continue;
			}
			queue.add(splits[i]); //这里不能用push，因为对LinkedList来说，add是顺序在后面插入，push是倒序从开头插入
		}
		
		// 生成最终串
		for (; !queue.isEmpty(); sb.append(queue.poll() + "/")) {}
		if ((len = sb.length()) > 1) { sb.deleteCharAt(len - 1); } // 如果一开头就有斜杠的话这里必须检查是不是还是只有一开始的斜杠
		// 这里还有一个办法，append改成插入到开头，这样就不需要LinkedList这样的双向结构，用单向的stack就可以，哪怕是反向输出，也可以早就正向结果。
        return sb.toString();
    }
	
	public static void main(String[] args) {
		String path = "/abc/...";
		System.out.println(simplifyPath(path));
	}
}
