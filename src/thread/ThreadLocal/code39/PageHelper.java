package thread.ThreadLocal.code39;

/**
 * @author ManJiis
 * @since 2020/9/14 18:44
 * @Description: // TODO
 **/
public class PageHelper {
    public static void startPage(Integer pageNum, Integer pageSize) {
        set(new Page(pageSize, pageNum));
    }

    private static ThreadLocal<Page> threadlocal = new ThreadLocal<>();
    public static Page get() {
        Page page = threadlocal.get();
        return page;
    }
    public static void set(Page page){
        threadlocal.set(page);
    }
}
class Page{
    int pageSize;
    int pageNum;
    public Page() {
    }
    public Page(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
