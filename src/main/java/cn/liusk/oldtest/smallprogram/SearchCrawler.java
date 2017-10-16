package cn.liusk.oldtest.smallprogram;

import java.util.*;  
import java.net.*;  
import java.io.*;  
import java.util.regex.*;  
// ����Web������  
public class SearchCrawler implements Runnable{  
   
/* disallowListCache����robot������������URL�� RobotЭ����Webվ��ĸ�Ŀ¼������һ��robots.txt�ļ�, 
  *�涨վ���ϵ���Щҳ�������������ġ� ��������Ӧ��������������������Щ����,������robots.txt��һ������: 
 # robots.txt for http://somehost.com/ 
   User-agent: * 
   Disallow: /cgi-bin/ 
   Disallow: /registration # /Disallow robots on registration page 
   Disallow: /login 
  */
  
  private HashMap< String,ArrayList< String>> disallowListCache = new HashMap< String,ArrayList< String>>();   
  ArrayList< String> errorList= new ArrayList< String>();//������Ϣ   
  ArrayList< String> result=new ArrayList< String>(); //�������Ľ��   
  String startUrl;//��ʼ���������  
  int maxUrl;//������url��  
  String searchString;//Ҫ�������ַ���(Ӣ��)  
  boolean caseSensitive=false;//�Ƿ����ִ�Сд  
  boolean limitHost=false;//�Ƿ������Ƶ�����������  
    
  public SearchCrawler(String startUrl,int maxUrl,String searchString){  
   this.startUrl=startUrl;  
   this.maxUrl=maxUrl;  
   this.searchString=searchString;  
  }  
   public ArrayList< String> getResult(){  
       return result;  
   }  
  public void run(){//���������߳�  
        
       crawl(startUrl,maxUrl, searchString,limitHost,caseSensitive);  
  }  
     
    //���URL��ʽ  
  private URL verifyUrl(String url) {  
    // ֻ����HTTP URLs.  
    if (!url.toLowerCase().startsWith("http://"))  
      return null;  
    URL verifiedUrl = null;  
    try {  
      verifiedUrl = new URL(url);  
    } catch (Exception e) {  
      return null;  
    }  
    return verifiedUrl;  
  }  
  // ���robot�Ƿ�������ʸ�����URL.  
 private boolean isRobotAllowed(URL urlToCheck) {   
    String host = urlToCheck.getHost().toLowerCase();//��ȡ����RUL������   
    //System.out.println("����="+host);  
    // ��ȡ����������������URL����   
    ArrayList< String> disallowList =disallowListCache.get(host);   
    // �����û�л���,���ز����档   
    if (disallowList == null) {   
      disallowList = new ArrayList< String>();   
      try {   
        URL robotsFileUrl =new URL("http://" + host + "/robots.txt");   
        BufferedReader reader =new BufferedReader(new InputStreamReader(robotsFileUrl.openStream()));   
        // ��robot�ļ���������������ʵ�·���б�   
        String line;   
        while ((line = reader.readLine()) != null) {   
          if (line.indexOf("Disallow:") == 0) {//�Ƿ����"Disallow:"   
            String disallowPath =line.substring("Disallow:".length());//��ȡ���������·��   
            // ����Ƿ���ע�͡�   
            int commentIndex = disallowPath.indexOf("#");   
            if (commentIndex != - 1) {   
              disallowPath =disallowPath.substring(0, commentIndex);//ȥ��ע��   
            }   
               
            disallowPath = disallowPath.trim();   
            disallowList.add(disallowPath);   
           }   
         }   
        // �����������������ʵ�·����   
        disallowListCache.put(host, disallowList);   
      } catch (Exception e) {   
              return true; //webվ���Ŀ¼��û��robots.txt�ļ�,������  
      }   
    }   
       
    String file = urlToCheck.getFile();   
    //System.out.println("�ļ�getFile()="+file);  
    for (int i = 0; i < disallowList.size(); i++) {   
      String disallow = disallowList.get(i);   
      if (file.startsWith(disallow)) {   
        return false;   
      }   
    }   
    return true;   
  }   
  
   
  private String downloadPage(URL pageUrl) {  
     try {  
        // Open connection to URL for reading.  
        BufferedReader reader =  
          new BufferedReader(new InputStreamReader(pageUrl.openStream()));  
        // Read page into buffer.  
        String line;  
        StringBuffer pageBuffer = new StringBuffer();  
        while ((line = reader.readLine()) != null) {  
          pageBuffer.append(line);  
        }  
          
        return pageBuffer.toString();  
     } catch (Exception e) {  
     }  
     return null;  
  }  
  // ��URL��ȥ��"www"  
  private String removeWwwFromUrl(String url) {  
    int index = url.indexOf("://www.");  
    if (index != -1) {  
      return url.substring(0, index + 3) +  
        url.substring(index + 7);  
    }  
    return (url);  
  }  
  // ����ҳ�沢�ҳ�����  
  private ArrayList< String> retrieveLinks(URL pageUrl, String pageContents, HashSet crawledList,  
    boolean limitHost)  
  {  
    // ��������ʽ�������ӵ�ƥ��ģʽ��  
    Pattern p =Pattern.compile("<a//s+href//s*=//s*/?(.*?)[/|>]",Pattern.CASE_INSENSITIVE);  
    Matcher m = p.matcher(pageContents);  
      
    ArrayList< String> linkList = new ArrayList< String>();  
    while (m.find()) {  
      String link = m.group(1).trim();  
        
      if (link.length() < 1) {  
        continue;  
      }  
      // ����������ҳ�������ӡ�  
      if (link.charAt(0) == '#') {  
        continue;  
      }  
        
      if (link.indexOf("mailto:") != -1) {  
        continue;  
      }  
       
      if (link.toLowerCase().indexOf("javascript") != -1) {  
        continue;  
      }  
      if (link.indexOf("://") == -1){  
        if (link.charAt(0) == '/') {//������Ե�    
          link = "http://" + pageUrl.getHost()+":"+pageUrl.getPort()+ link;  
        } else {           
          String file = pageUrl.getFile();  
          if (file.indexOf('/') == -1) {//������Ե�ַ  
            link = "http://" + pageUrl.getHost()+":"+pageUrl.getPort() + "/" + link;  
          } else {  
            String path =file.substring(0, file.lastIndexOf('/') + 1);  
            link = "http://" + pageUrl.getHost() +":"+pageUrl.getPort()+ path + link;  
          }  
        }  
      }  
      int index = link.indexOf('#');  
      if (index != -1) {  
        link = link.substring(0, index);  
      }  
      link = removeWwwFromUrl(link);  
      URL verifiedLink = verifyUrl(link);  
      if (verifiedLink == null) {  
        continue;  
      }  
      /* ����޶��������ų���Щ����������URL*/  
      if (limitHost &&  
          !pageUrl.getHost().toLowerCase().equals(  
            verifiedLink.getHost().toLowerCase()))  
      {  
        continue;  
      }  
      // ������Щ�Ѿ����������.  
      if (crawledList.contains(link)) {  
        continue;  
      }  
       linkList.add(link);  
    }  
   return (linkList);  
  }  
 // ��������Webҳ������ݣ��ж��ڸ�ҳ������û��ָ���������ַ���  
  private boolean searchStringMatches(String pageContents, String searchString, boolean caseSensitive){  
       String searchContents = pageContents;   
       if (!caseSensitive) {//��������ִ�Сд  
          searchContents = pageContents.toLowerCase();  
       }  
      
    Pattern p = Pattern.compile("[//s]+");  
    String[] terms = p.split(searchString);  
    for (int i = 0; i < terms.length; i++) {  
      if (caseSensitive) {  
        if (searchContents.indexOf(terms[i]) == -1) {  
          return false;  
        }  
      } else {  
        if (searchContents.indexOf(terms[i].toLowerCase()) == -1) {  
          return false;  
        }  
      }     }  
    return true;  
  }  
    
  //ִ��ʵ�ʵ���������  
  public ArrayList< String> crawl(String startUrl, int maxUrls, String searchString,boolean limithost,boolean caseSensitive )  
  {   
      
    System.out.println("searchString="+searchString);  
    HashSet< String> crawledList = new HashSet< String>();  
    LinkedHashSet< String> toCrawlList = new LinkedHashSet< String>();  
     if (maxUrls < 1) {  
        errorList.add("Invalid Max URLs value.");  
        System.out.println("Invalid Max URLs value.");  
      }  
    
      
    if (searchString.length() < 1) {  
      errorList.add("Missing Search String.");  
      System.out.println("Missing search String");  
    }  
      
    if (errorList.size() > 0) {  
      System.out.println("err!!!");  
      return errorList;  
      }  
      
    // �ӿ�ʼURL���Ƴ�www  
    startUrl = removeWwwFromUrl(startUrl);  
      
    toCrawlList.add(startUrl);  
    while (toCrawlList.size() > 0) {  
        
      if (maxUrls != -1) {  
        if (crawledList.size() == maxUrls) {  
          break;  
        }  
      }  
      // Get URL at bottom of the list.  
      String url =  toCrawlList.iterator().next();  
      // Remove URL from the to crawl list.  
      toCrawlList.remove(url);  
      // Convert string url to URL object.  
      URL verifiedUrl = verifyUrl(url);  
      // Skip URL if robots are not allowed to access it.  
      if (!isRobotAllowed(verifiedUrl)) {  
        continue;  
      }  
      
      // �����Ѵ����URL��crawledList  
      crawledList.add(url);  
      String pageContents = downloadPage(verifiedUrl);  
        
      if (pageContents != null && pageContents.length() > 0){  
        // ��ҳ���л�ȡ��Ч������  
        ArrayList< String> links =retrieveLinks(verifiedUrl, pageContents, crawledList,limitHost);  
       
        toCrawlList.addAll(links);  
        if (searchStringMatches(pageContents, searchString,caseSensitive))  
        {  
          result.add(url);  
          System.out.println(url);  
        }  
     }  
      
    }  
   return result;  
  }  
  // ������  
  public static void main(String[] args) {  
    String[] params = new String[3];
    params[0] = "http://www.sina.com";
    params[1] = "10";
    params[2] = "index";
    int max=Integer.parseInt(params[1]);  
    SearchCrawler crawler = new SearchCrawler(params[0],max,params[2]);  
    Thread  search=new Thread(crawler);  
    System.out.println("Start searching...");  
    System.out.println("result:");  
    search.start();  
     
  }  
} 