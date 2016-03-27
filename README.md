# xsd2thrift-maven-plugin


A maven plugin frontend for (https://travis-ci.org/tranchis/xsd2thrift). The tool converts XML Schema files (.xsd) to Thrift (.thrift) and
Protocol Buffers (.proto) schemas.



### Usage


			<plugin>
				<groupId>de.anatelos.maven.plugins</groupId>
				<artifactId>xsd2thrift-maven-plugin</artifactId>
				<version>${xsd2thrift.version}</version>
				<executions>
					<execution>
						<id>generate-proto</id>
						<phase>generate-sources</phase>
						<goals>
							<goal>parsexsd</goal>
						</goals>
						<configuration>
							<protocol>protobuf</protocol><!-- default -->
							<outputDir>target/generated-sources/proto</outputDir><!-- default -->
							<packageName>packagename</packageName>
							<xschemaList>
								<param>test.xsd</param>
								<param>xschema/anothertest.xsd</param>
							</xschemaList>
						</configuration>
					</execution>
				</executions>
			</plugin>


### Building

You need Maven (http://maven.apache.org/) installed. To build xsd2thrift-maven-plugin, run `mvn install`.

xsd2thrift-maven-plugin has been compiled with Java 8. You may want to adjust source/target settings in the pom.


### License

The code contributed for this package is licensed under LGPL v3 (see LICENSE).

XSOM is covered by GPL v2 with classpath exception (see LICENSE-xsom.1).

XSOM internally uses an URI class, which is copyrighted by Thai Open Source
Center (see LICENSE-xsom.2).

### Contact

Any feedback will be greatly appreciated, at the GitHub project page
(http://github.com/xorejing/xsd2thrift-maven-plugin)



