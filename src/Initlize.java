import ca.beq.util.win32.registry.RegistryKey;
import ca.beq.util.win32.registry.RegistryValue;
import ca.beq.util.win32.registry.RootKey;
import ca.beq.util.win32.registry.ValueType;


public class Initlize{
	
	public static void init(){
		
		System.load("D://jRegistryKey.dll");
		//д��ע���
		RegistryKey newKey = new RegistryKey(RootKey.HKEY_CURRENT_USER,"Environment\\NewKey");
		newKey.create();//�����µļ�HKEY_CURRENT_USER/Environment/NewKey
		RegistryValue value = new RegistryValue("My Paht",ValueType.REG_SZ,"hahah\\lalal");
		newKey.setValue(value);//���´����ļ���д���ֵ,��ע����а�F5ˢ��һ���ܿ����仯�˰�
	}
	
	public static void main(String[] args) {
		init();
	}

}
