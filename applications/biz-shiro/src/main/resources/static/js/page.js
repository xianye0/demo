var page = new Vue({
    el:'#page',
    data(){
        return {
            page:{
                pageIndex:0,
                pageSize:10,
                total:0,
                pageCount:0
            }
        }
    }
});
