/*
 * Project Name		:	Board
 * 
 * File Name		:	Member.java
 * Revision			:	0.2
 * Date				:	2021.08.04
 * Author			:	Lee KwangHyo
 * 
 */


package j_collection;

import java.util.HashMap;

public class Member {
	HashMap<String, Object> instance = new HashMap<String, Object>();
	
	public Member(int memId, String iD, String pW, int memType) {
		this.instance.put("memId", memId);
		this.instance.put("ID", iD);
		this.instance.put("PW", pW);
		this.instance.put("memType", memType);
	}
	
	public Member(){
		
	}
	
	public int getMemId(){
		return (int)this.instance.get("memId");
	}

	public String getID(){
		return (String)this.instance.get("ID");
	}

	public int getPassword(){
		return (int)this.instance.get("PW");
	}

	public int getMemType(){
		return (int)this.instance.get("memType");
	}
	
}
