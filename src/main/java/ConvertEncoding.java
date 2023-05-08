import java.io.File;
import java.io.UnsupportedEncodingException;

/*
* @author Laolata
* @description 编码转换工具,支持将文件夹下的任意格式编码转化为指定格式
* @blog 我的博客地址 https://blog.csdn.net/itkfdektxa
* */
public class ConvertEncoding {
    /*定义转换后的编码格式*/
    private final static String targetCharset = "GBK";
    /*定义转换计数器*/
    private static int count = 0;
    /*
    * @description 递归方法,递归指定目录下所有文件夹与文件,并重命名
    * @param originFile 递归文件或目录
    * */
    public static void renameFiles(File originFile) throws UnsupportedEncodingException {
        //递归出口
        if(originFile.isFile()){
            //对文件进行重命名
            byte[] newName = originFile.getName().getBytes(targetCharset);
            //将字节码转换为String
            String newNameStr = new String(newName);
            //创建新的完整路径
            String newPath = originFile.getParent()+File.separator+newNameStr;
            //重命名
            originFile.renameTo(new File(newPath));
            //计数器加一
            count++;
        }else{
            //文件夹递归调用自身
            File[] files = originFile.listFiles();
            for(File f:files){
                renameFiles(f);
            }
        }
    }
    /*
    * @description 测试方法
    * */
    public static void main(String[] args) throws UnsupportedEncodingException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\hawen");
        System.out.println("Go......");
        renameFiles(file);
        System.out.println("yes:  "+count+"");
    }
}
