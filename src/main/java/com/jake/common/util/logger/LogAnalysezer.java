package com.jake.common.util.logger;

import com.jake.common.util.collections.HashMap8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
    请用java实现以下shell脚本的功能

    cat /home/admin/logs/webx.log | grep "Login" | uniq -c | sort -nr
    //找出包含login的，去重，排序
    
    A000
    A000
    A000
    B000
    A000
    
    | grep '000' | uniq -c 结果：
    3 A000
    1 B000
    1 A000

    变种
    4 A000
    1 B000
    
**/


public class LogAnalysezer{

    /** 文件读取器 */
    private FileReader fileReader;
    
    /** 缓冲读取器 */
    private BufferedReader bufferedReader;

    public LogAnalysezer(String file) {
        this.init(file);
    }
    
    
    /**
    * 初始化日志分析器
    */
    private void init(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            this.fileReader = fileReader;
            this.bufferedReader = bufferedReader;
        }catch(Exception e) {
            throw new RuntimeException("初始化日志解析器异常", e);
        }
    
    }
    
    /**
     * 关闭日志分析器
     */
    public void close() {
        if (fileReader != null) {
            try {
                fileReader.close();
            }catch(IOException e){
                // ignore
            }
              
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            }catch(IOException e) {
                //ignore
            }
        }
    }


    public ListStream select(String param, Filter filter) {

        return null;
    }
    
    
    /**
    * <pre>
    * 统计出现单词的行数<br/>
    * <pre>
    * @param word 目标单词
    * @return ListMap.Entry<Integer, String>> key : 出现次数 value:行内容
    */
    public List<Map.Entry<Integer, String>> countRepeatLinesWithWord(String word) {
        if(word == null || "".equals(word)) {
            throw new IllegalArgumentException("参数word不能为空");
        }

        List<Map.Entry<Integer, String>> result = new ArrayList<Map.Entry<Integer, String>>();
        
        
        // 统计符合条件的行的数量
        Map<String, Integer> findLines = new HashMap8<String, Integer>(100);
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.indexOf(word) == -1) {
                    continue;
                }
                Integer count = findLines.get(line);
                if (count == null) {
                    findLines.put(line, count + 1);
                } else {
                    findLines.put(line, 1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("读取文件异常", e);
        }
        
        // 获取结果
        for (final Map.Entry<String, Integer> entry : findLines.entrySet()) {
            result.add(new Map.Entry<Integer, String>() {

                final String key = entry.getKey();
                final Integer value = entry.getValue();

                @Override
                public Integer getKey() {
                    return value;
                }

                @Override
                public String getValue() {
                    return key;
                }

                @Override
                public String setValue(String value) {
                    throw new UnsupportedOperationException();
                }
            });
            
        }
        
        return result;
        
    }
    
    /**
    * 输出结果 (降序)
    * @param list List<Map.Entry<Integer, String>>
    */
    private static void printResult(List<Map.Entry<Integer, String>> list) {
        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return o2.getKey() - o1.getKey();
            }
        });
        for (Map.Entry<Integer, String> entry : list) {
            System.out.println(entry.getKey() + "\t" +  entry.getValue());
        }
    }
    
    
    /**
    * 测试代码
    */
    public static void main(String[] args) throws IOException {
        String file= args[0];// 目标文件
        String word = args[1];// 目标单词
        LogAnalysezer logAnalysezer = new LogAnalysezer(file);

        List<Map.Entry<Integer, String>> result = logAnalysezer.countRepeatLinesWithWord(word);// 统计
        printResult(result);// 打印
        
        logAnalysezer .close();// 关闭
    }
    
    

}