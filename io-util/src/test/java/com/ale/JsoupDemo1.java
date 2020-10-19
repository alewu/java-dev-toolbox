package com.ale;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author alewu
 * @date 2020/10/17
 */
public class JsoupDemo1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String dir = "E:\\tmp\\极客时间\\101-后端技术面试38讲";
        String targetDir = "E:\\tmp\\101-后端技术面试38讲";
        File file = new File(targetDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        List<Path> files = FileUtils.getFiles(dir);
        List<Path> htmlFiles =
                files.stream().filter(path -> path.toFile().getName().endsWith("html")).collect(Collectors.toList());
        Path path1 = htmlFiles.get(0);
        List<Path> paths = Collections.singletonList(path1);
        for (Path path : paths) {
            CompletableFuture.runAsync(() -> {
                try {
                    dealOne(targetDir, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(30);

    }

    private static void dealOne(String targetDir, Path path) throws IOException {
        System.out.println(path.toFile().getName());
        Document document = Jsoup.parse(path.toFile(), StandardCharsets.UTF_8.name());

        Elements meta = document.getElementsByTag("meta");
        for (Element element : meta) {
            String name = element.attr("name");
            if (name.equals("description")) {
                element.remove();
            }
            if (name.equals("Keywords")) {
                element.remove();
            }
        }
        JsoupUtils.replaceText(document, "加微信", "王宝令");

        List<String> tags = ImmutableList.of("script");
        JsoupUtils.removeByTag(document, tags);

        JsoupUtils.removeByText(document, "欢迎在留言区与我分享你的想法，也欢迎你在留言区记录你的思考过程。感谢阅读，如果你觉得这篇文章对你有帮助的话，也欢迎把它分享给更多的朋友。");

        List<String> ids = ImmutableList.of("qb_collection_img_mask", "gkui-modal-controller");
        JsoupUtils.removeById(document, ids);

        // 按css删除
        List<String> divClassList = ImmutableList.of("Wz6esVdU_0", "_1Bg5E78Y_0 _25ls2Q2l_0", "_3FoXPaWx_0");
        JsoupUtils.removeByClassName(document, divClassList);

        String imageUrl = "https://static001.geekbang.org/resource/image/cf/aa/cf393cd748a4f0e6451807c4b61843aa" +
                ".jpg";
        String attrKey = "data-savepage-src";
        JsoupUtils.removeImage(document, attrKey, imageUrl);


        String html = document.html();
        System.out.println(html);
        Files.write(html.getBytes("UTF-8"), Paths.get(targetDir, path.toFile().getName()).toFile());
    }


}
