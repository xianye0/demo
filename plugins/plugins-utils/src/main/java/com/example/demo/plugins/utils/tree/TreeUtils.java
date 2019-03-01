package com.example.demo.plugins.utils.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wulei
 * @date: 2018/11/6
 * @Description:
 */
public class TreeUtils {
    private TreeUtils() {

    }

    /**
     * 将list转为tree，会比较code和parentCode，parentCode为null或""时为一级
     * @param l 需要转换成树型结构的原list
     * @return 树型结构list
     */
    public static List listToTree(List l) {
        List<Tree> list = new ArrayList();
        List<Tree> cList = new ArrayList();
        for (Object type : l) {
            Tree tree = (Tree)type;
            if (tree.getParentCode() == null || "".equals(tree.getParentCode())) {
                list.add(tree);
            } else {
                cList.add(tree);
            }
        }
        for (Tree t : list) {
            createChildList(t, cList);
        }
        return list;
    }



    private static void createChildList(Tree type, List<Tree> tempList) {
        if (tempList == null || tempList.isEmpty()) {
            return;
        }
        List<Tree> childList = new ArrayList();
        for (int i = tempList.size() - 1; i >= 0; i--) {
            Tree child = tempList.get(i);
            if (type.getCode().equals(child.getParentCode())) {
                createChildList(child, tempList);
                childList.add(child);
                tempList.remove(i);
            }
        }
        if (isSortable(type)){
            childList.sort((a,b)->((Sortable)a).getOrder()-((Sortable)b).getOrder());
        }
        type.setSubList(childList);
    }

    /**
     * 是否排序
     * @param o 对象是否支持排序
     * @return yes/no
     */
    public static boolean isSortable(Object o){
        return o instanceof Sortable;
    }
}
