/*
 * Project Name		:	Board
 * 
 * File Name		:	PostTable.java
 * Revision			:	0.2
 * Date				:	2021.08.04
 * Author			:	Lee KwangHyo
 * 
 */

package j_collection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PostTable {
	ArrayList<Post> postTable = new ArrayList<Post>();
	int postCnt = 0;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yy.MM HH:mm");
	
	Post search(int postNo){
		for(Post walk : postTable){
			if((int)walk.instance.get("postNo") == postNo){
				return walk;
			}
		}
		System.out.println("해당게시글이 없습니다.");
		return null;
	}
	Post addPost(String title, String content, Member member){
		Post temp = new Post(postCnt, title, content, member);
		postTable.add(temp);
		postCnt++;
		return temp;
	}
	void printPostTable(MemberTable memberTable){
		System.out.printf("%4s", "글번호");
		System.out.printf("%8s", "제목");
		System.out.printf("%72s", "작성자");
		System.out.printf("%10s", "게시일");
		System.out.println("\n========================================================================");
		for(int i = postTable.size() - 1; i >= 0; i--){
			System.out.printf("%4s", postTable.get(i).getPostNo());
			System.out.printf("%8s", postTable.get(i).getTitle());
			System.out.printf("%37s", memberTable.search(postTable.get(i).getMemId()).getID());
			System.out.printf("%16s", sdf.format(postTable.get(i).getChangeDate()));
			System.out.println("\n========================================================================");
		}
		System.out.println();
	}
	void printPost(int postNo, MemberTable memberTable){
		Post temp = new Post();
		temp = search(postNo);
		SimpleDateFormat tmpSdf = new SimpleDateFormat("HH:mm");
		System.out.printf("%4s", ("[" + temp.getTitle() + "]"));
		System.out.printf("%44s", memberTable.search(temp.getMemId()).getID());
		System.out.printf("%10s", tmpSdf.format(temp.getPostDate()));
		System.out.printf("%6s", tmpSdf.format(temp.getChangeDate()));
		System.out.println("\n========================================================================");
		System.out.println(temp.instance.get("content"));
		System.out.println("========================================================================");
	}
	void deletePost(int postNo){
		Post flag = new Post();
		flag = search(postNo);
		if(flag == null){
			System.out.println("해당 게시글 없어서 삭제 불가");
		}else{
			postTable.remove(postTable.indexOf(flag));
		}
	}
	void changePost(int postNo, String title, String content, Member member){
		Post flag = new Post();
		flag = search(postNo);
		if(flag == null){
			System.out.println("해당 게시글 없어서 수정 불가");
		}else if(getAccess(postNo, member)){
			postTable.get(postTable.indexOf(flag)).instance.put("title",title);
			postTable.get(postTable.indexOf(flag)).instance.put("content",content);
			postTable.get(postTable.indexOf(flag)).instance.put("changeDate",new Date());
		}else{
			System.out.println("수정 권한이 없습니다.");
		}
	}
	boolean getAccess(int postNo, Member member){
		Post flag = new Post();
		flag = search(postNo);
		if((int)flag.getMemType() > (int)member.getMemType()){
			System.out.println("접근할 수 없는 게시글입니다.");
			return false;
		}else if((int)flag.getMemType() == (int)member.getMemType()){
			if((int)member.getMemId() == (int)flag.getMemId()){
				System.out.println("접근권한이 있습니다.");
				return true;
			}else{
				System.out.println("접근할 수 없는 게시글입니다.");
				return false;
			}
		}else{
			System.out.println("접근권한이 있습니다.");
			return true;
		}
	}
	
}
