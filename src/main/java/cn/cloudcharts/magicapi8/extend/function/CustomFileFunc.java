package cn.cloudcharts.magicapi8.extend.function;

import cn.hutool.core.lang.Console;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.ssssssss.script.annotation.Comment;
import org.ssssssss.magicapi.core.config.MagicFunction;
import org.ssssssss.script.annotation.Function;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wuque
 * @title: MyCustomFunction
 * @projectName magic-api8
 * @description: 自定义函数
 * @date 2023/6/117:55
 */
@Component
public class CustomFileFunc implements MagicFunction {

    public static File transferToFile(MultipartFile multipartFile) {
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file=File.createTempFile(filename[0], filename[1] + ".");
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     *
     * @param multipartFile 文件流
     * @param row_begin 开始行
     * @param col_begin 开始列
     * @param heaer_row_num 列头所在行
     * @return
     */
    @Function
    @Comment("读取CSV文件")
    public static  List<Map<String,String>> extractCSV(MultipartFile multipartFile,int row_begin,int col_begin, int heaer_row_num) {

        File file = transferToFile(multipartFile);

        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(file);

        List<String> header = new LinkedList<>();

        List<CsvRow> rows = data.getRows();
        int size = rows.size();
        for (int i = 0; i < size; i++) {
            //只读指定的表头行
            if(i + 1 == heaer_row_num){
                CsvRow csvRow = rows.get(i);
                List<String> cols = csvRow.getRawList();
                for(int j = cols.size()-1;j>=0;j--){
                    if(j < col_begin - 1){
                        cols.remove(j);
                    }
                }
                header = csvRow.getRawList().stream().filter(s -> ObjectUtils.isNotEmpty(s)).collect(Collectors.toList());
                break;
            }
        }
        Console.log("列头:"+header);

        int colNum = header.size();
        List<Map<String,String>> dataMapList = new LinkedList<>();

        for (int i = 0; i < rows.size(); i++) {

            //从指定行读取
            if(i + 1 >= row_begin){
                CsvRow csvRow = rows.get(i);
                List<String> cols = csvRow.getRawList();
                //从指定列读取
                for(int j = cols.size()-1;j>=0;j--){
                    if(j < col_begin - 1){
                        cols.remove(j);
                    }
                }
                //封装数据行
                Map dataMap = Maps.newHashMap();
                for (int j = 0; j < cols.size(); j++) {
                    if(j < colNum){
                        dataMap.put(header.get(j),cols.get(j));
                    }
                }
                dataMapList.add(dataMap);
            }
        }
//        Console.log("数据项："+dataMapList);

        return dataMapList;
    }


}
