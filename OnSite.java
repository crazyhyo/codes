/*
 * Project Name		:	Board
 * 
 * File Name		:	OnSite.java
 * Revision			:	0.3
 * Date				:	2021.08.05
 * Author			:	Lee KwangHyo
 * 
 */

package j_collection;

import play.ScanUtil;

public class OnSite {

	MemberTable memberTable		= new MemberTable();
	PostTable postTable 		= new PostTable();
	Status status 				= Status.valueOf("LOGIN");
	Member currentMember 		= null;
	Post currentPost 			= null;
	boolean flag 				= true;
	
	public OnSite(){
		
	}
	
	void run(){
		
		int input = 0;
		
		memberTable.addMember("asdf", "111", 0);
		memberTable.addMember("qwer", "222", 1);
		memberTable.addMember("zxcv", "333", 1);
		
		postTable.addPost("ASDF", "A S D F", memberTable.memberTable.get(0));
		postTable.addPost("QWER", "Q W E R", memberTable.memberTable.get(1));
		postTable.addPost("ZXCV", "Z X C V", memberTable.memberTable.get(2));
		
		while(flag){
			PrintStatus.print(status, memberTable, postTable, currentPost);
			input = ScanUtil.nextInt();
			flag = updateStatus(status, input);
			System.out.println(status);
		}
	}
	
	boolean updateStatus(Status status, int input){
		switch(status){
		case LOGIN :
			if(input == 1){
				if(login(0)){
					this.status = Status.valueOf("BOARD");
				}
			}else if(input == 2){
				this.status = Status.valueOf("N_SIGN_UP");
			}else if(input == 3){
				if(login(1)){
					this.status = Status.valueOf("BOARD");
				}
			}else if(input == 4){
				this.status = Status.valueOf("A_SIGN_UP");
			}else if(input == 5){
				this.status = Status.valueOf("F_PASSWORD");
			}else if(input == 6){
				return false;
			}
			break;
			
		case N_SIGN_UP :
			if(input == 1){
				if(signIn(0)){
					this.status = Status.valueOf("LOGIN");
				}
			}else if(input == 2){
				this.status = Status.valueOf("LOGIN");
			}
			break;
			
		case A_SIGN_UP :
			if(input == 1){
				if(signIn(1)){
					this.status = Status.valueOf("LOGIN");
				}
			}else if(input == 2){
				this.status = Status.valueOf("LOGIN");
			}
			break;
			
		case F_PASSWORD :
			if(input == 1){
				findPassword();
			}else if(input == 2){
				this.status = Status.valueOf("LOGIN");
			}
			break;
			
		case BOARD :
			if(input == 1){
				if(selectPost()){
					this.status = Status.valueOf("POST_DETAIL");
				}
			}else if(input == 2){
				this.status = Status.valueOf("POST_ADD");
			}else if(input == 3){
				if(logOut()){
					this.status = Status.valueOf("LOGIN");
				}
			}
			break;
			
		case POST_DETAIL :
			if(input == 1){
				if(accessGranted()){
					this.status = Status.valueOf("POST_CHANGE");
				}
			}else if(input == 2){
				if(accessGranted()){
					this.status = Status.valueOf("POST_DELETE");
				}
			}else if(input == 3){
				if(backBoard()){
					this.status = Status.valueOf("BOARD");
				}
			}
			break;
			
		case POST_ADD :
			if(input == 1){
				if(addPost()){
					this.status = Status.valueOf("POST_DETAIL");
				}
			}else if(input == 2){
				this.status = Status.valueOf("BOARD");
			}
			break;
			
		case POST_CHANGE :
			if(input == 1){
				changePost();
			}else if(input == 2){
				this.status = Status.valueOf("POST_DETAIL");
			}
			break;
			
		case POST_DELETE :
			if(input == 1){
				if(deletePost()){
					this.status = Status.valueOf("BOARD");
				}
			}else if(input == 2){
				this.status = Status.valueOf("POST_DETAIL");
			}
			break;
		
		}
		return true;
	}
	
	String inputId(){
		System.out.print("아이디를 입력해주세요>");
		return ScanUtil.nextLine();
	}

	String inputPassword(){
		System.out.print("비밀번호를 입력해주세요>");
		return ScanUtil.nextLine();
	}
	int inputPostNo(){
		System.out.println("조회할 게시글을 선택해주세요>");
		return ScanUtil.nextInt();
	}
	
	String inputTitle(){
		System.out.print("제목을 입력해주세요>");
		return ScanUtil.nextLine();		
	}

	String inputContent(){
		System.out.print("내용을 입력해주세요>");
		return ScanUtil.nextLine();		
	}
	
	boolean login(int type){
		Member tmpMember	= new Member();
		String tmpId 		= null;
		String tmpPassword 	= null;
		
		tmpId = inputId();
		tmpMember = memberTable.search(tmpId);
		if(tmpMember == null){
			return false;
		}else{
			tmpPassword = inputPassword();
			tmpMember = memberTable.search(tmpId, tmpPassword, type);
		}
		if(tmpMember != null){
			this.currentMember = tmpMember;
		}else{
			return false;
		}
		return true;
	}
	
	boolean signIn(int type){
		Member tmpMember	= new Member();
		String tmpId 		= null;
		String tmpPassword 	= null;
		tmpId = inputId();
		tmpMember = memberTable.search(tmpId);
		if(tmpMember == null){
			tmpPassword = inputPassword();
			memberTable.addMember(tmpId, tmpPassword, type);
		}else{
			System.out.println("회원가입 실패, 중복아이디 존재");
			return false;
		}
		return true;
	}
	
	boolean findPassword(){
		Member tmpMember	= new Member();
		String tmpId 		= null;
		tmpId = inputId();
		tmpMember = memberTable.search(tmpId);
		if(tmpMember == null){
			return false;
		}else{
			System.out.println("비밀번호는 다음과 같습니다.");
			System.out.println(tmpMember.instance.get("PW"));
			
			System.out.println();
		}
		return true;
	
	}
	
	boolean selectPost(){
		Post tmpPost		= new Post();
		int tmpPostNo		= 0;
		tmpPostNo = inputPostNo();
		tmpPost = postTable.search(tmpPostNo);
		if(tmpPost == null){
			return false;
		}else{
			currentPost = tmpPost;
		}
		return true;
	}
	
	boolean logOut(){
		this.currentMember = null;
		return true;
	}
	
	boolean accessGranted(){
		if(postTable.getAccess((int)currentPost.instance.get("postNo"), currentMember)){

		}else{
			System.out.println("권한이 없습니다");
			return false;
		}
		return true;
	}
	
	boolean backBoard(){
		currentPost = null;
		return true;
	}
	
	boolean addPost(){
		String tmpTitle		= null;
		String tmpContent	= null;
		tmpTitle = inputTitle();
		tmpContent = inputContent();
		currentPost = postTable.addPost(tmpTitle, tmpContent, currentMember);
		return true;
	}
	
	
	boolean changePost(){
		String tmpTitle		= null;
		String tmpContent	= null;
		if(accessGranted()){
			postTable.changePost((int)currentPost.instance.get("postNo"), tmpTitle, tmpContent, currentMember);
			return true;
		}
		else{
			return false;
		}
	}
	
	boolean deletePost(){
		if(accessGranted()){
			postTable.deletePost((int)currentPost.instance.get("postNo"));
			currentPost = null;
			return true;
		}else{
			return false;
		}
	}
	
}
