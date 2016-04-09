package de.anatelos.maven.plugins;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Kommentar zur Klasse
 * 
 * <pre>
 * Nr.   Name            Datum     Ausl�ser (ggf. Release) / Beschreibung
 * ----  --------------  --------  ------------------------------------------------
 * *02*
 * *01*  ........        TT.MM.JJ  Neuanlage
 * </pre>
 */
public class SaxEntityResolver implements EntityResolver {

	private Map<String, String> resolverMap;

	public SaxEntityResolver() {
		this(new HashMap<String, String>());
	}

	/**
	 * Konstruktor
	 * 
	 * @param resolverMap
	 */
	public SaxEntityResolver(Map<String, String> resolverMap) {
		this.resolverMap = resolverMap;
	}

	public void addMapping(String remoteSysId, String localSysId) {
		resolverMap.put(remoteSysId, localSysId);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
	 */
	@Override
	public InputSource resolveEntity(String publicId, String remoteSystemId) throws SAXException, IOException {

		String resolvedId = null;
		if (publicId != null && remoteSystemId != null) {
			resolvedId =
					remoteSystemId.startsWith("file:") ? remoteSystemId : new File(resolverMap.get(remoteSystemId))
							.toURI().toURL().toExternalForm();
		}
		else if (remoteSystemId != null) {
			resolvedId = new File(resolverMap.get(remoteSystemId)).toURI().toURL().toExternalForm();
		}

		if (resolvedId != null) {
			InputSource source = new InputSource(resolvedId);
			source.setPublicId(publicId);
			return source;
		}
		return null;
	}

}
