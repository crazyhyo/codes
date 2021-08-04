package j_collection;

public class PrintStatus {
	static void print(Status status, MemberTable memberTable, PostTable postTable, Post currentPost){
		switch(status){
		case LOGIN :
			printLogin(memberTable);
			break;
			
		case N_SIGN_UP :
			printNormalSignUp();
			break;
			
		case A_SIGN_UP :
			printAdminSignUp();
			break;
			
		case F_PASSWORD :
			printFindPassword();
			break;
			
		case BOARD :
			printBoard(memberTable, postTable);
			break;
			
		case POST_DETAIL :
			printPostDetail(memberTable, postTable, currentPost);
			break;
			
		case POST_ADD :
			printPostAdd();
			break;
			
		case POST_CHANGE :
			printPostChange();
			break;
			
		case POST_DELETE :
			printPostDelete();
			break;
						
		}
		
	}
	
	static void printLogin(MemberTable memberTable){
		
		memberTable.printMemberTable();
		System.out.println("1일반회원로그인\t\t2일반회원회원가입\t\t3관리자로그인");
		System.out.println("4관리자회원가입\t\t5비밀번호찾기\t\t6사이트나가기");
		
	}
	
	static void printNormalSignUp(){
		System.out.println("1일반회원가입\t\t2돌아가기");
	}
	
	static void printAdminSignUp(){
		System.out.println("1관리자회원가입\t\t2돌아가기");
	}
	
	static void printFindPassword(){
		System.out.println("1비밀번호찾기\t\t2돌아가기");
	}
	
	static void printBoard(MemberTable memberTable, PostTable postTable){
		postTable.printPostTable(memberTable);
		
		System.out.println("\t\t\t\t1게시글조회\t2게시글등록\t3로그아웃");
	}
	
	static void printPostDetail(MemberTable memberTable, PostTable postTable, Post currentPost){
		postTable.printPost(currentPost.getPostNo(), memberTable);
		System.out.println("\t\t\t\t1게시글수정\t2게시글삭제\t3돌아가기");
	}
	
	static void printPostAdd(){
		System.out.println("\t\t\t\t\t\t1게시글등록\t2돌아가기");
	}
	
	static void printPostChange(){
		System.out.println("\t\t\t\t\t\t1게시글수정\t2돌아가기");
	}
	
	static void printPostDelete(){
		System.out.println("\t\t\t\t\t\t1게시글삭제\t2돌아가기");
	}
	
	
}
