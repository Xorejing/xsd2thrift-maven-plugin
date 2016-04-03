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
							<debug>false</debug><!-- default -->
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

xsd2thrift-maven-plugin has been compiled with Java SE 7.

The fork  (https://github.com/Xorejing/xsd2thrift) , branch feature/maven-plugin includes all needed modifications. These modifications will be added to xsd2thrift eventually.


### License

The code contributed for this package is licensed under LGPL v3 (see LICENSE.LGPL).

xsd2thrift is covered by LGPL v3 (see LICENSE.LGPL).

XSOM is covered by CDDL1.1 (see LICENSE.CDDL+GPL_1.1.html).

XSOM internally uses an URI class, which is copyrighted by Thai Open Source
Center (see LICENSE.ThaiOpenSource).

### Contact

Any feedback will be greatly appreciated, at the GitHub project page
(http://github.com/xorejing/xsd2thrift-maven-plugin).



