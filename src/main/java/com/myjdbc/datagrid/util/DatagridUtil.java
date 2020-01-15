package com.myjdbc.datagrid.util;

import com.myjdbc.core.util.FileUtil;
import com.myjdbc.core.util.JsonUtil;
import com.myjdbc.core.util.StringUtil;
import com.myjdbc.datagrid.annotation.DatagridColumn;
import com.myjdbc.datagrid.annotation.DatagridTable;
import com.myjdbc.datagrid.entity.DataGrid;
import com.myjdbc.datagrid.entity.TableColumn;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 陈文
 * @Date 2019/12/17  23:19
 * @Description DataGrid工具类
 */
public class DatagridUtil {

    /**
     * @Author 陈文
     * @Date 2019/12/17  23:19
     * @Description 获取DataGrid要展示的列
     */
    private static List<TableColumn> getTableColumns(Class cls) {
        Field[] fields = cls.getDeclaredFields();
        List<TableColumn> list = new ArrayList<>();
        for (Field field : fields) {
            DatagridColumn datagridColumn = field.getAnnotation(DatagridColumn.class);
            if (datagridColumn != null) {
                TableColumn tableColumnBo = new TableColumn();
                String fieldId;
                String title;
                if (StringUtils.isEmpty(datagridColumn.fieldId())) {
                    fieldId = field.getName();
                } else {
                    fieldId = datagridColumn.fieldId();
                }
                if (StringUtils.isEmpty(datagridColumn.title())) {
                    title = field.getName();
                } else {
                    title = datagridColumn.title();
                }
                tableColumnBo.setId(fieldId);
                tableColumnBo.setTitle(title);
                tableColumnBo.setHidden(datagridColumn.hidden());
                tableColumnBo.setRowspan(datagridColumn.rowspan());
                tableColumnBo.setColspan(datagridColumn.colspan());

                list.add(tableColumnBo);
            }
        }
        return list;
    }

    /**
     * @Author 陈文
     * @Date 2019/12/18  11:41
     * @Description 获取DataGrid要展示的列
     * 多行表头，可以通过建立从文件的方式实现
     */
    public static List<List<TableColumn>> getColumns(Class cls) {
        List<List<TableColumn>> columns = new ArrayList<>();
        //类文件绝对路径
        String path = cls.getResource("").getPath();
        //类的包路径
        String pack = cls.getPackage().getName();
        //类所在文件夹
        File file = new File(path);
        //类名
        String fileName = cls.getSimpleName();
        //类所在包下所有文件
        File[] files = file.listFiles();
        if (files != null) {
            //从文件记录器，用于给从文件排序
            Map<Integer, Class<?>> classMap = new HashMap<>();
            for (File file1 : files) {
                //包中文件名
                String fileName1 = FileUtil.getFileName(file1.getName());
                if (fileName.equals(fileName1)) {
                    //包中文件排除掉传入的主要类
                    continue;
                }
                //保重文件主体和主类相同（即主类名和包中其他类名前面字符相等），认定是从文件
                if (fileName.equals(StringUtil.leftSubstring(fileName1, fileName.length()))) {
                    //组合从文件全限定类名
                    String className = pack + "." + fileName1;
                    try {
                        //加载从文件
                        Class<?> clazz = Class.forName(className);
                        DatagridTable datagridTable = clazz.getAnnotation(DatagridTable.class);
                        if (datagridTable != null)
                        //记录从文件
                        {
                            classMap.put(datagridTable.index(), clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            for (int i = 1; i <= classMap.size(); i++) {
                //按照DatagridTable注解指定的顺序加载多表头列
                //不支持跨行加载（如1直接到3，缺省2，会报错）
                columns.add(getTableColumns(classMap.get(i)));
            }
        }

        //最后再加载主类（文件）的列
        columns.add(getTableColumns(cls));
        return columns;
    }


    public static void printDataGrid(HttpServletResponse response, DataGrid dataGrid) {
        String json = JsonUtil.BoTojson(dataGrid);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        try {
            PrintWriter pw = response.getWriter();
            pw.write(json);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
