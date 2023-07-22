package mapreduce;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

public class WordUdtf extends GenericUDTF {
    //声明数组
    Object[] objects=new Object[1];

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //列名的类型
        List<String> list = new ArrayList<>();
        //设置列名
        list.add("opten");
        //设置检查其列表
        ArrayList<ObjectInspector> objectInspectors=new ArrayList<>();
        //设置输出的列值的类型
        objectInspectors.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        //调用getStandardStyuctObjectInspector方法返回出去 第一个参数写列名的集合  第二个参数是字段类型的集合
        return ObjectInspectorFactory.getStandardStructObjectInspector(list,objectInspectors);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        //原始数据
        String s = args[0].toString();
        //切分
        String[] s1 = s.split(" ");
        for (String str:s1) {
            //将每个单词添加到值得对象数组中去
            objects[0]=str;
            //将对象数组的内容写出
            forward(objects);
        }
    }

    @Override
    public void close() throws HiveException {

    }
}
