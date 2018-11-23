// File basedir = new File( "." );
File file = new File( basedir, "build.log" );
assert file.exists();

String buildLog = file.getText("UTF-8");
//assert !buildLog.contains('org.apache.maven.project.ProjectBuildingException');

assert !buildLog.contains('net.sourceforge.cobertura.reporting.xml.XMLReport');

assert new File( basedir, "target/failsafe-reports/failsafe-summary.xml").exists();
assert new File( basedir, "target/site/failsafe-report.html").exists();

assert new File( basedir, "target/cpd.xml" ).exists();
assert new File( basedir, "target/site/cpd.html" ).exists();

assert new File( basedir, "target/pmd.xml" ).exists();
assert new File( basedir, "target/site/pmd.html" ).exists();

assert new File( basedir, "target/spotbugsXml.xml" ).exists();
assert new File( basedir, "target/site/spotbugs.html" ).exists();

assert new File( basedir, "target/checkstyle-result.xml" ).exists();
assert new File( basedir, "target/site/checkstyle.html" ).exists();

assert new File( basedir, "target/site/jacoco/index.html" ).exists();
assert new File( basedir, "target/site/jacoco/jacoco.xml" ).exists();

assert new File( basedir, "target/site/xref/index.html" ).exists();

assert new File( basedir, "target/apidocs/index.html" ).exists();
assert new File( basedir, "target/site/apidocs/index.html" ).exists();

if (System.getenv('CI') == null) {
    assert new File( basedir, "target/site/github-report.html").exists();
}

File[] files = new File( basedir, "target/repo/net/oneandone/maven/poms/sample-project-jacoco/3000-SNAPSHOT/").listFiles();
assert files.toString().findAll("sample-project-jacoco-3000-.*-sources\\.jar").size()>0;
assert files.toString().findAll("sample-project-jacoco-3000-.*-javadoc\\.jar").size()>0;

return true;
