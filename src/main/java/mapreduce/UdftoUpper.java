package mapreduce;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


public class UdftoUpper extends UDF {
    public Text evaluate(Text a){
        Text text = new Text(a.toString().toUpperCase());
        return text;
    }
}
