package edu.umb.cs681.hw01.part2.fs;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.IntSummaryStatistics;
import java.util.List;

import edu.umb.cs681.hw01.part2.fs.util.FileCrawlingVisitor;
import static java.util.stream.Collectors.*;


public class Main {

    public static void main(String[] args) {
        // repo directory
        Directory repo = new Directory(null, "repo", 0, LocalDateTime.now());

        // create repo/src directory
        Directory repoSrc = new Directory(repo, "src", 0, LocalDateTime.now());
        // create repo/src/A.java file
        File repoSrcA = new File(repoSrc, "A.java", 100, LocalDateTime.now());
        // create repo/src/B.java file
        File repoSrcB = new File(repoSrc, "B.java", 200, LocalDateTime.now());

        // create repo/bin directory
        Directory repoBin = new Directory(repo, "bin", 0, LocalDateTime.now());
        // create repo/bin/A.class file
        File repoBinA = new File(repoBin, "A.class", 300, LocalDateTime.now());
        // create repo/bin/B.class file
        File repoBinB = new File(repoBin, "B.class", 400, LocalDateTime.now());

        // create repo/test directory
        Directory repoTest = new Directory(repo, "test", 0, LocalDateTime.now());

        // create repo/test/src directory
        Directory repoTestSrc = new Directory(repoTest, "src", 0, LocalDateTime.now());
        // create repo/test/src/A.java file
        File repoTestSrcA = new File(repoTestSrc, "A.java", 100, LocalDateTime.now());
        // create repo/test/src/B.java file
        File repoTestSrcB = new File(repoTestSrc, "B.java", 200, LocalDateTime.now());
        // create repo/test/bin directory
        Directory repoTestBin = new Directory(repoTest, "bin", 0, LocalDateTime.now());
        // create repo/test/bin/A.class file
        File repoTestBinA = new File(repoTestBin, "A.class", 300, LocalDateTime.now());
        // create repo/test/bin/B.class file
        File repoTestBinB = new File(repoTestBin, "B.class", 400, LocalDateTime.now());

        // create repo/readme.md file
        File repoReadme = new File(repo, "readme.md", 500, LocalDateTime.now());


        // File crawling visitor instance
        FileCrawlingVisitor visitor = new FileCrawlingVisitor();
        repo.accept(visitor);



        
        Map<String, IntSummaryStatistics> groupedFilesBasedOnExtension = visitor.files()
            .collect(
                groupingBy(
                    file -> {
                        if (file.getName().endsWith(".class")) {
                            return ".class";
                        }
                        if (file.getName().endsWith(".java")) {
                            return ".java";
                        }
                        return "other";
                    },
                    summarizingInt(File::getSize)
                )
            );

        System.out.println("Files grouped based on extension .java: " + groupedFilesBasedOnExtension.get(".java"));
        System.out.println("Files grouped based on extension .class: " + groupedFilesBasedOnExtension.get(".class"));
        System.out.println("Files grouped based on extension other: " + groupedFilesBasedOnExtension.get("other"));
    }

}
