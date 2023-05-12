package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public Helper () {};
	public static void buttonTextChanger() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.okButtonText", "Tamam");
	}
	public static void showMsg(String str)  {
		buttonTextChanger();
		String msg;
		switch (str) {
		case "fill":
			msg = "L�tfen t�m alanlar� doldurunuz !";
			break;
		case "success":
			msg = "��lem tamamland� !";
			break;
		case "error":
			msg = "Bir hata olu�tu !";
			break;
		default:
			msg = str;
		}
		JOptionPane.showMessageDialog(null, msg, "Uyar�", JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean control (String str) {
		buttonTextChanger();
		String msg;
		switch (str) {
		case "sure":
			msg = "Bu i�lemi yapmak i�in emin misiniz ?";
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
