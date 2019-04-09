package com.example.demo.plugins.utils.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: rock
 * @date: 2019/2/19
 * @Description:
 */
public class CacheMapMonitor {
    private static List<Map<Object, CacheMap.CacheValue>> list = Collections.synchronizedList(new ArrayList<>());
    private static CacheMapMonitor instance;



    private CacheMapMonitor(){
        Thread t = new Thread(()->{
            while (true){
                for(Map<Object, CacheMap.CacheValue> map:list){
                    for(Map.Entry<Object, CacheMap.CacheValue> entry:map.entrySet()){
                        if(entry.getValue().isExpired()){
                            map.remove(entry.getKey());
                        }
                    }
                    try{
                        Thread.sleep(5000);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public static CacheMapMonitor getInstance(){
        synchronized (CacheMapMonitor.class){
            if(instance == null){
                instance = new CacheMapMonitor();
            }
        }
        return instance;
    }

    public void addMap(Map map){
        list.add(map);
    }


}
