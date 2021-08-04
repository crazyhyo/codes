/*
 * Project Name		:	Board
 * 
 * File Name		:	Post.java
 * Revision			:	0.2
 * Date				:	2021.08.04
 * Author			:	Lee KwangHyo
 * 
 */

package j_collection;

import java.util.Date;
import java.util.HashMap;

public class Post {
	HashMap<String, Object> instance = new HashMap<String, Object>();
	
	public Post(){
		
	}
	public Post(int postNo, String title, String content, Member member){
		this.instance.put("postNo", postNo);
		this.instance.put("title", title);
		this.instance.put("content", content);
		this.instance.put("memId", member.instance.get("memId"));
		this.instance.put("postDate", new Date());
		this.instance.put("changeDate", new Date());
		this.instance.put("memType", member.instance.get("memType"));
	}
	
	
	public int getPostNo(){
		return (int)this.instance.get("postNo");
	}
	public String getTitle(){
		return (String)this.instance.get("title");
	}
	public String getContent(){
		return (String)this.instance.get("content");
	}
	public Date getPostDate(){
		return (Date)this.instance.get("postDate");
	}
	public Date getChangeDate(){
		return (Date)this.instance.get("changeDate");
	}
	public int getMemId(){
		return (int)this.instance.get("memId");
	}
	public int getMemType(){
		return (int)this.instance.get("memType");
	}
}
