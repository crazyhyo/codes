/*
 * Project Name		:	Board
 * 
 * File Name		:	MemberTable.java
 * Revision			:	0.2
 * Date				:	2021.08.04
 * Author			:	Lee KwangHyo
 * 
 */

package j_collection;

import java.util.ArrayList;

public class MemberTable {
	ArrayList<Member> memberTable = new ArrayList<Member>();
	int memberCnt = 0;
	
	public MemberTable(){
		
	}
	
	void printMemberTable(){
		System.out.println("회원번호\t\t아이디\t\t비밀번호\t\t구분");
		for(Member walk : memberTable){
			System.out.print(walk.instance.get("memId"));
			System.out.print("\t\t" + walk.instance.get("ID"));
			System.out.print("\t\t" + walk.instance.get("PW"));
			System.out.println("\t\t" + ((int)walk.instance.get("memType") == 0 ? "일반회원" : "관리자회원"));
		}
		System.out.println();
	}

	Member search(String ID){
		for(Member walk : memberTable){
			if(((String)walk.instance.get("ID")).equals(ID))
				return walk;
		}
//		System.out.println("해당 ID를 가진 회원이 없습니다.");
		return null;
	}
	
	Member search(String ID, String PW, int type){
		for(Member walk : memberTable){
			if(((String)walk.instance.get("ID")).equals(ID)
					&& ((String)walk.instance.get("PW")).equals(PW)
					&& (int)walk.instance.get("memType") == type){
//				System.out.println("회원찾기 성공");
				return walk;
			}
		}
		System.out.println("비밀번호가 맞지 않습니다.");
		return null;
	}
	Member search(int memId){
		for(Member walk : memberTable){
			if((int)walk.instance.get("memId") == memId){
//				System.out.println("회원찾기 성공");
				return walk;
			}
		}
		System.out.println("해당회원이 없습니다.");
		return null;
	}
	boolean addMember(String ID, String PW, int type){
		Member flag = new Member();
		flag = search(ID);
		if(flag == null){
//			System.out.println("중복아이디 없음(회원가입 성공)");
			flag = new Member(memberCnt, ID, PW, type);
			memberTable.add(flag);
			memberCnt++;
			return true;
		}else{
//			System.out.println("중복아이디 있음(회원가입 실패)");
			return false;
		}
	}
	String getPW(String ID){
		Member flag = new Member();
		flag = search(ID);
		if(flag == null){
			System.out.println("해당 아이디없음");
			return null;
		}else{
			return (String)flag.instance.get("PW");
		}
	}
	
}
