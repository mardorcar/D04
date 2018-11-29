package utilities;



import security.Authority;
import security.UserAccount;

public class CommonUtilities {

	public static boolean isAdmin(UserAccount userAccount){
		boolean isAdmin = false;
		
		for (Authority autoAuthority : userAccount.getAuthorities()) {
			if (autoAuthority.getAuthority().equals(Authority.ADMIN)) {
				isAdmin = true;
				break;
			}
		}
		
		return isAdmin;
	}
	
	public static boolean isCustomer(UserAccount userAccount){
		boolean isCustomer = false;
		
		for (Authority autoAuthority : userAccount.getAuthorities()) {
			if (autoAuthority.getAuthority().equals(Authority.CUSTOMER)) {
				isCustomer = true;
				break;
			}
		}
		
		return isCustomer;
	}
	public static boolean isHandyWorker(UserAccount userAccount){
		boolean isHW = false;
		
		for (Authority autoAuthority : userAccount.getAuthorities()) {
			if (autoAuthority.getAuthority().equals(Authority.HANDYWORKER)) {
				isHW = true;
				break;
			}
		}
		
		return isHW;
	}
	 
}
