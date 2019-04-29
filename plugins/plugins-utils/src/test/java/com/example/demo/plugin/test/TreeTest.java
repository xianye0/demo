package com.example.demo.plugin.test;

import com.example.demo.plugins.utils.tree.Tree;
import com.example.demo.plugins.utils.tree.TreeUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: rock
 * @date: 2019/4/29
 * @Description:
 */
@RunWith(SpringRunner.class)
public class TreeTest {

    @Test
    public void test(){
        List<Tree> l = new ArrayList();
        l.add(new TreeVo("a",""));
        l.add(new TreeVo("a1","a"));
        l.add(new TreeVo("a2","a1"));
        l.add(new TreeVo("a3","a2"));
        l.add(new TreeVo("b",""));
        l.add(new TreeVo("b1","b"));
        l.add(new TreeVo("b2","b1"));
        l.add(new TreeVo("d","c"));
        l.add(new TreeVo("d1","d"));
        l.add(new TreeVo("d2","d1"));
        List<TreeVo> ll = TreeUtils.listToTree(l);
        System.out.println(ll);
    }

    class TreeVo implements Tree{
        private String code;
        private String parentCode;
        private List<TreeVo> children;

        public TreeVo(String code,String parentCode){
            this.code = code;
            this.parentCode = parentCode;
        }

        @Override
        public Object getCode() {
            return code;
        }

        @Override
        public void setCode(Object code) {
            this.code = (String)code;
        }

        @Override
        public Object getParentCode() {
            return parentCode;
        }

        @Override
        public void setParentCode(Object parentCode) {
            this.parentCode = (String)parentCode;
        }

        @Override
        public List getChildren() {
            return children;
        }

        @Override
        public void setChildren(List subList) {
            this.children = subList;
        }
    }
}
