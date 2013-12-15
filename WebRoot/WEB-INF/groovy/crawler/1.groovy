import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.obullxl.lang.web.crawl.AbstractWebCrawler;
import com.github.obullxl.lang.utils.TextUtils;

public class CnblogsWebCrawler extends AbstractWebCrawler {

    public List<String> parseLinks(String url) {
        List<String> links = new ArrayList<String>();

        Document document = Jsoup.connect(url).get();
        Element body = document.body();
        Elements posts = body.getElementsByClass("titlelnk");
        for (Element post : posts) {
            links.add(post.attr("href"));
        }
        return links;
    }

    public String parseTitle(Document document) {
        // MyBatis3与Spring3无缝集成-从iBatis平滑过渡 - 老牛啊 - 博客园
        String title = StringUtils.trim(document.title());

        // MyBatis3与Spring3无缝集成-从iBatis平滑过渡 - 老牛啊
        title = StringUtils.trim(StringUtils.substringBeforeLast(title, "-"));

        // MyBatis3与Spring3无缝集成-从iBatis平滑过渡
        title = StringUtils.trim(StringUtils.substringBeforeLast(title, "-"));

        return title;
    }

    public String parseSummary(Document document) {
        Element body = document.body();
        if (body == null) {
            return StringUtils.EMPTY;
        }

        Element blog = body.getElementById("cnblogs_post_body");
        if (blog == null) {
            return StringUtils.EMPTY;
        }
        
        blog.ownText();

        String summary = StringUtils.trim(StringUtil.normaliseWhitespace(blog.text()));
        return TextUtils.truncate(summary, 255);
    }

    public String parseContent(Document document) {
        Element body = document.body();
        if (body == null) {
            return StringUtils.EMPTY;
        }

        Element blog = body.getElementById("cnblogs_post_body");
        if (blog == null) {
            return StringUtils.EMPTY;
        }

        return StringUtils.trim(blog.html());
    }

}
			