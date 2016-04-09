/**
 * 
 */
package de.anatelos.maven.plugins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.xml.sax.EntityResolver;

import com.github.tranchis.xsd2thrift.Xsd2Thrift;
import com.github.tranchis.xsd2thrift.Xsd2ThriftException;

/**
 * The xsd2thrift-maven-plugin creates
 * <ul>
 * <li>Google protocol buffer descriptions (.proto files)</li>
 * <li>Apache Thrift descriptions (.thrift files)</li>
 * </ul>
 * from a list of xschema files
 *
 * 
 * @author marug
 * 
 */
@Mojo(name = "parsexsd", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class Xsd2ThriftMojo extends AbstractMojo {

	@Parameter(property = "parsexsd.xschemaList")
	private List<String> xschemaList;

	@Parameter(property = "parsexsd.outputDir", defaultValue = "target/generated-sources/proto")
	private String outputDir;

	@Parameter(property = "parsexsd.packageName")
	private String packageName;

	@Parameter(defaultValue = "protobuf")
	private String protocol;

	@Parameter
	private boolean nestEnums = true;

	@Parameter
	private Properties resolverProperties;

	@Parameter(defaultValue = "false")
	private boolean debug;

	private String fileSuffix;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		this.checkFileSuffix();
		try {
			Xsd2Thrift xsd2thrift = new Xsd2Thrift(packageName, protocol, nestEnums);

			Map<String, String> resolverMap = createResolverMap(resolverProperties);
			EntityResolver entityResolver = new SaxEntityResolver(resolverMap);

			for (String xschema : xschemaList) {
				String outputFile = outputDir + xschema.substring(xschema.lastIndexOf("/"), xschema.indexOf(".xsd"))
				        + fileSuffix;
				xsd2thrift.parseXsd(xschema, outputFile, entityResolver, debug);
			}
		} catch (Xsd2ThriftException e) {
			throw new MojoExecutionException(e.getMessage(), null);
		}

	}

	private Map<String, String> createResolverMap(Properties resolverProps) {
		if (null == resolverProps || resolverProps.isEmpty()) {
			return null;
		}
		Map<String, String> resolverMap = new HashMap<>();
		for (Object prop : resolverProps.keySet()) {
			resolverMap.put((String) prop, resolverProps.getProperty((String) prop));
		}
		return resolverMap;
	}

	private void checkFileSuffix() {
		if (null == fileSuffix) {
			fileSuffix = "protobuf".equals(protocol) ? ".proto"
			        : ("thrift".equals(protocol) ? ".thrift" : ("protobuf3".equals(protocol) ? ".proto" : ".bug"));
		}
	}
}
