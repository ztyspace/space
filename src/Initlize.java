import ca.beq.util.win32.registry.RegistryKey;
import ca.beq.util.win32.registry.RegistryValue;
import ca.beq.util.win32.registry.RootKey;
import ca.beq.util.win32.registry.ValueType;


public class Initlize{
	
	public static void init(){
		
		System.load("D://jRegistryKey.dll");
		//写入注册表
		RegistryKey newKey = new RegistryKey(RootKey.HKEY_CURRENT_USER,"Environment\\NewKey");
		newKey.create();//创建新的键HKEY_CURRENT_USER/Environment/NewKey
		RegistryValue value = new RegistryValue("My Paht",ValueType.REG_SZ,"hahah\\lalal");
		newKey.setValue(value);//在新创建的键里写入键值,在注册表中按F5刷新一下能看到变化了吧
	}
	
	public static void main(String[] args) {
		init();
	}

}
