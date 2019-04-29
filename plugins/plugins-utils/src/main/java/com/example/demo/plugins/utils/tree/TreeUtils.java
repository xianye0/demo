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
     * 将list转换为树型结构
     * @param l
     * @return
     */
    public static List listToTree(List l) {
        List<Tree> first = new ArrayList();
        List<Tree> other = new ArrayList();
        List<Tree> notFirst = new ArrayList();


        //将parentCode为空的数据取出来作为一级树
        for (Object type : l) {
            Tree tree = (Tree) type;
            if (tree.getParentCode() == null || "".equals(tree.getParentCode())) {
                first.add(tree);
            }else {
                other.add(tree);
            }
        }

        //剩余节点如果存在父节点则不为一级
        for (int i = other.size()-1;i>=0;i--) {
            Tree tree = other.get(i);
            for(int j = i-1;j>=0;j--){
                Tree tree1 = other.get(j);
                if(tree1.getParentCode().equals(tree.getCode())){
                    notFirst.add(other.remove(j));
                }else if(tree.getParentCode().equals(tree1.getCode())){
                    notFirst.add(other.remove(i));
                    break;
                }
            }
        }

        //剩余节点的父节点在一级节点中存在则为非一级节点
        for (int i = other.size()-1;i>=0;i--) {
            Tree tree = other.get(i);
            for(Tree tree1 :first){
                if(tree.getParentCode().equals(tree1.getCode())){
                    notFirst.add(other.remove(i));
                    break;
                }
            }
        }
        //剩余节点的父节点在一级结点中不存在，则为一级节点
        first.addAll(other);
        //生成树
        for (Tree t : first) {
            createChildList(t, notFirst);
        }
        return first;
    }



    private static void createChildList(Tree type, List<Tree> tempList) {
        if (tempList == null || tempList.isEmpty()) {
            return;
        }
        List<Tree> childList = new ArrayList();
        for (int i = tempList.size() - 1; i >= 0; i--) {
            if (i >= tempList.size()) {
                break;
            }
            Tree child = tempList.get(i);
            if (type.getCode().equals(child.getParentCode())) {
                createChildList(child, tempList);
                childList.add(child);
                tempList.remove(child);
            }
        }
        if (isSortable(type)) {
            childList.sort((a, b) -> ((Sortable) a).getOrder() - ((Sortable) b).getOrder());
        }
        type.setChildren(childList);
    }

    /**
     * 是否排序
     * @param o 对象是否支持排序
     * @return yes/no
     */
    public static boolean isSortable(Object o) {
        return o instanceof Sortable;
    }
}
