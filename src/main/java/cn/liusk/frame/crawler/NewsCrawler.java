/**
 * BEYONDSOFT.COM INC
 */
package cn.liusk.frame.crawler;

import org.jsoup.nodes.Document;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 爬虫
 * @author liusk
 * @version $Id: NewsCrawler.java, v 0.1 2017年8月11日 下午2:02:08 liusk Exp $
 */
public class NewsCrawler extends BreadthCrawler {

    /**
     * @param crawlPath crawlPath is the path of the directory which maintains
     * information of this crawler
     * @param autoParse if autoParse is true,BreadthCrawler will auto extract
     * links which match regex rules from pag
     */
    public NewsCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        /*start page*/
        //this.addSeed("http://www.cs.zut.edu.cn");
        this.addSeed("http://www.zzti.edu.cn/");

        /*fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml*/
        //this.addRegex("http://www.cs.zut.edu.cn/info/1633/.*.htm");
        this.addRegex("http://www.zzti.edu.cn/info/1041/*.htm");
        /*do not fetch jpg|png|gif*/
        this.addRegex("-.*\\.(jpg|png|gif).*");
        /*do not fetch url contains #*/
        this.addRegex("-.*#.*");
    }

    public static void main(String[] args) throws Exception {
        NewsCrawler crawler = new NewsCrawler("crawl", true);
        crawler.setThreads(50);
        crawler.setTopN(100);
        //crawler.setResumable(true);
        /*start crawl with depth of 4*/
        crawler.start(4);
    }

    /** 
     * @see cn.edu.hfut.dmic.webcollector.fetcher.Visitor#visit(cn.edu.hfut.dmic.webcollector.model.Page, cn.edu.hfut.dmic.webcollector.model.CrawlDatums)
     */
    public void visit(Page page, CrawlDatums next) {

        String url = page.url();
        System.out.println("-------------------------------------------------" + url);
        /*if page is news page*/
        if (page.matchUrl("http://www.zzti.edu.cn/info/1041/*.htm")) {
            /*we use jsoup to parse page*/
            Document doc = page.doc();

            //System.out.println(doc.html());

            /*extract title and content of news by css selector*/
            /*String title = page.doc().select("td[class=titlestyle43843]").html();
            String content = page.doc().select("form[name=form43843a]").html();
            String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
            String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式 
            Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(content);
            content = m_script.replaceAll(""); //过滤script标签 
            Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            Matcher m_style = p_style.matcher(content);
            content = m_style.replaceAll(""); //过滤style标签 
            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(content);
            content = m_html.replaceAll(""); //过滤html标签
            */

            String title = page.doc().select("td[class=titlestyle67215]").html();
            //String content = page.doc().select("div[class=name_info]").html();

            System.out.println("title--------" + title);
            //System.out.println("content---------"+content);

            /*If you want to add urls to crawl,add them to nextLink*/
            /*WebCollector automatically filters links that have been fetched before*/
            /*If autoParse is true and the link you add to nextLinks does not 
              match the regex rules,the link will also been filtered.*/
            //next.add("http://xxxxxx.com");
        }
    }
}
