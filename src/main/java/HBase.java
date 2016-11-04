/**
 * Created by felicien on 03/11/16.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HBase {

    private static Configuration conf = null;
    private static String tableName;
    /**
     * Initialization
     */
    static {
        conf = HBaseConfiguration.create();
    }

        public HBase(String tableName,String[] familys) throws IOException {
            this.tableName=tableName;
            HBaseAdmin admin = new HBaseAdmin(conf);
            if (!admin.tableExists(tableName)) {
                HTableDescriptor tableDesc = new HTableDescriptor(tableName);
                for (int i = 0; i < familys.length; i++) {
                    tableDesc.addFamily(new HColumnDescriptor(familys[i]));
                }
                admin.createTable(tableDesc);
                System.out.println("create table " + tableName + " ok.");
            }
        }



    public static void addRecord(String rowKey,
                                 String family, String qualifier, String value) throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Put put = new Put(Bytes.toBytes(rowKey));
            put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
            table.put(put);
            System.out.println("insert recored " + rowKey + " to table "
                    + tableName + " ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllRecord () {
        try{
            HTable table = new HTable(conf, tableName);
            Scan s = new Scan();
            ResultScanner ss = table.getScanner(s);
            for(Result r:ss){
                for(KeyValue kv : r.raw()){
                    System.out.print(new String(kv.getRow()) + " ");
                    System.out.print(new String(kv.getFamily()) + ":");
                    System.out.print(new String(kv.getQualifier()) + " ");
                    System.out.print(kv.getTimestamp() + " ");
                    System.out.println(new String(kv.getValue()));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
