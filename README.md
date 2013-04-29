Parent POM for 1-and-1 open source projects.

Contains common configuration for projects that use github and Sonatype OSS Repository Hosting.
Default License is Apache 2.0.

Latest Jenkins-Build: [![Build Status](http://huschteguzzel.de/hudson/job/oneandone-foss-parent/badge/icon)](http://huschteguzzel.de/hudson/job/oneandone-foss-parent/)

Changelog
---------
http://1and1.github.com/foss-parent/github-report.html

How to use the POM
------------------

* for general information about using a parent pom take a look at http://maven.apache.org/guides/introduction/introduction-to-the-pom.html.
* it boils down to define a parent element in your own POM:

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>net.oneandone.maven.poms</groupId>
        <artifactId>foss-parent</artifactId>
        <version>${project.version}</version>
    </parent>
    <groupId>sample-groupId</groupId>
    <artifactId>sample-project</artifactId>
    <version>1-SNAPSHOT</version>
    [...]
</project>
```

Latest releases
---------------

... may be found at [Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22net.oneandone.maven.poms%22%20AND%20a%3A%22foss-parent%22%20AND%20p%3A%22pom%22
).


Rules for versions
------------------

* **Major** version bump for cases where the user of the ``pom`` MUST change or add stuff in her project (new file is needed, plugin or dependency not defined in the ``pom``anymore)
* **Minor** version bump for cases where versions of managed dependencies or managed plugins receive a major update so most of the times a quick fix would be to freeze the versions of the aforementioned dependencies or plugins in the project's ``pom``. This includes additions of managed plugins or dependencies as well.
* **Micro** version bump for cases where where versions of managed dependencies or managed plugins receive a minor update.

Releasing a project
-------------------

... to [Maven Central](http://maven.apache.org/guides/mini/guide-central-repository-upload.html) via [Sonatype OSS Repository Hosting](https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide):

* add the following to your settings:

>     <settings>
>       <servers>
>         <server>
>           <id>sonatype-nexus-snapshots</id>
>           <username>sonatype-user</username>
>           <password>sonatype-pwd</password>
>         </server>
>         <server>
>           <id>sonatype-nexus-staging</id>
>           <username>sonatype-user</username>
>           <password>sonatype-pwd</password>
>         </server>
>       </servers>
>     </settings>

* `mvn release:prepare`
* `mvn release:perform`
* close and release staging repository: https://oss.sonatype.org/index.html#stagingRepositories

TODO: http://www.sonatype.com/books/nexus-book/reference/staging-sect-managing-plugin.html

Deploy site to github
---------------------

To deploy a site to github using [maven-scm-publish-plugin](http://maven.apache.org/plugins/maven-scm-publish-plugin/):

* Add a new branch `gh-pages` [manually](https://help.github.com/articles/creating-project-pages-manually).
* Introduce a property `scmpublish.content` (normally `<scmpublish.content>target/staging/ARTIFACTID</scmpublish.content>`).
* Add `src/site/resources/.nojekyll` [to add hidden files (e.g. for jacoco)](http://illegalstateexception.blogspot.de/2013/01/publishing-maven-site-having-jacoco.html) as well.
* Run `mvn clean verify site site:stage scm-publish:publish-scm`.


Adjust license headers
----------------------

>     mvn com.mycila.maven-license-plugin:maven-license-plugin:format

