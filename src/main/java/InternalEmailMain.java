import org.hyy.mns.core.EmailUtil;

public class InternalEmailMain {
	
	public static void main(String[] args) {
		EmailUtil.newInstance().sendNotification("yaoyu.he@thomsonreuters.com", "Test, Pls ignore.", "Test");
		System.out.println("Done");
	}
	
}
