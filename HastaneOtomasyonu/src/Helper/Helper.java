package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public Helper () {};
	public static void buttonTextChanger() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.okButtonText", "Tamam");
	}
	public static void showMsg(String str)  {
		buttonTextChanger();
		String msg;
		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanlarý doldurunuz !";
			break;
		case "success":
			msg = "Ýþlem tamamlandý !";
			break;
		case "error":
			msg = "Bir hata oluþtu !";
			break;
		default:
			msg = str;
		}
		JOptionPane.showMessageDialog(null, msg, "Uyarý", JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean control (String str) {
		buttonTextChanger();
		String msg;
		switch (str) {
		case "sure":
			msg = "Bu iþlemi yapmak için emin misiniz ?";
			break;
		default:
			msg = str;
			break;
		}
		int res = JOptionPane.showConfirmDialog(null, msg,"Dikkat!",JOptionPane.YES_NO_OPTION);
		if (res == 0) {
			return true;
		}
		else {
			return false;
		}
	}
} 
