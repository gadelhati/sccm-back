package br.com.fattoria.sccm.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class MainTest {
	
	private static File file = new File("C:\\Users\\Victor\\Desktop\\teste.xml");

	private static void salvarArquivo(String documento) throws Exception {
		
		FileOutputStream fos = new FileOutputStream(file, true);
		fos.write(documento.getBytes());
		fos.flush();
		fos.close();
		
	}
	
	private static String converter(Document document) throws TransformerException {
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(document);
		transformer.transform(source, result);
		
		String xmlString = result.getWriter().toString();
		
		System.out.append(xmlString);
		
		return xmlString;
		
	}
	
	private static void gerarXml(ContatoTest contato) throws Exception {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document doc = db.newDocument();
		
		Element tagContato = doc.createElement("contato");
		
		Element idContato    = doc.createElement("id");
		Element nomeContato  = doc.createElement("nome");
		Element emailContato = doc.createElement("email");
		
		idContato.setTextContent(String.valueOf(contato.getId()));
		nomeContato.setTextContent(contato.getNome());
		emailContato.setTextContent(contato.getEmail());
		
		tagContato.appendChild(idContato);
		tagContato.appendChild(nomeContato);
		tagContato.appendChild(emailContato);
		
		Element tagFones = doc.createElement("Telefones");
		
		Element tagFone = null;
		
		Element idFone;
		Element dddFone;
		Element numeroFone;
		
		for (TelefoneTest fone : contato.getTelefones()) {
			
			tagFone = doc.createElement("Telefone");
			
			idFone = doc.createElement("id");
			dddFone = doc.createElement("ddd");
			numeroFone = doc.createElement("numero");
			
			idFone.setTextContent(String.valueOf(fone.getId()));
			dddFone.setTextContent(String.valueOf(fone.getDdd()));
			numeroFone.setTextContent(String.valueOf(fone.getNumero()));
			
			tagFone.appendChild(idFone);
			tagFone.appendChild(dddFone);
			tagFone.appendChild(numeroFone);
			
			tagFones.appendChild(tagFone);			
		}
		
		tagContato.appendChild(tagFones);
		
		Element tagEndereco = doc.createElement("Endereco");
		
		Element idEnd = doc.createElement("id");
		Element logradouroEnd = doc.createElement("logradouro");
		Element bairroEnd = doc.createElement("bairro");
		Element cepEnd = doc.createElement("cep");
		Element cidadeEnd = doc.createElement("cidade");
		Element complementoEnd = doc.createElement("complemento");
		Element numeroEnd = doc.createElement("numero");
		
		idEnd.setTextContent(String.valueOf(contato.getEndereco().getId()));
		logradouroEnd.setTextContent(contato.getEndereco().getLogradouro());
		bairroEnd.setTextContent(contato.getEndereco().getBairro());
		cepEnd.setTextContent(contato.getEndereco().getCep());
		cidadeEnd.setTextContent(contato.getEndereco().getCidade());
		complementoEnd.setTextContent(contato.getEndereco().getComplemento());
		numeroEnd.setTextContent(String.valueOf(contato.getEndereco().getNumero()));
		
		tagEndereco.appendChild(idEnd);
		tagEndereco.appendChild(logradouroEnd);
		tagEndereco.appendChild(bairroEnd);
		tagEndereco.appendChild(cepEnd);
		tagEndereco.appendChild(cidadeEnd);
		tagEndereco.appendChild(complementoEnd);
		tagEndereco.appendChild(numeroEnd);
		
		tagContato.appendChild(tagEndereco);
		
		doc.appendChild(tagContato);
		
		String arquivo = converter(doc);
		
		salvarArquivo(arquivo);
		
	}
	
	public static String getChildTagValue(Element element, String tagName) throws Exception {
		
		NodeList children = element.getElementsByTagName(tagName);
		String result = null;
		
		if (children == null) {
			return result;
		}
		
		Element child = (Element) children.item(0);
		
		if (child == null) {
			return result;
		}
		
		result = child.getTextContent();
		
		return result;
	}
	
	private static ContatoTest lerXml() throws Exception, SAXException, TransformerException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document doc = db.parse(new InputSource(file.toString()));
		
		Element raiz = doc.getDocumentElement();
		
		EnderecoTest endereco = new EnderecoTest();
		
		NodeList endList = raiz.getElementsByTagName("Endereco");
		Element endElement = (Element) endList.item(0);
		endereco.setId(Integer.parseInt(getChildTagValue(endElement, "id")));
		endereco.setNumero(Integer.parseInt(getChildTagValue(endElement, "numero")));
		endereco.setBairro(getChildTagValue(endElement, "bairro"));
		endereco.setCep(getChildTagValue(endElement, "cep"));
		endereco.setCidade(getChildTagValue(endElement, "cidade"));
		endereco.setComplemento(getChildTagValue(endElement, "complemento"));
		endereco.setLogradouro(getChildTagValue(endElement, "logradouro"));
		
		List<TelefoneTest> telefones = new ArrayList<TelefoneTest>();
		NodeList fonesList = raiz.getElementsByTagName("Telefone");
		Element foneElement;
		//Fazemos um for nas tags Telefone e adicionamos os dados
		//  em um objeto Telefone e depois na colecao
		for (int i = 0; i < fonesList.getLength(); i++) {
			foneElement = (Element) fonesList.item(i);
			TelefoneTest telefone = new TelefoneTest();
			telefone.setId(Integer.parseInt(getChildTagValue(foneElement, "id")));
			telefone.setDdd(Integer.parseInt(getChildTagValue(foneElement, "ddd")));
			telefone.setNumero(Integer.parseInt(getChildTagValue(foneElement, "numero")));
			//Adicionamos a coleção as tags Telefone
			telefones.add(telefone);
		}

		//Agora iremos ler os dados de Contato
		//Como esses dados estao apenas dentro
		// da tag Contato e de mais nenhuma outra
		// vamos entao usar o elemento raiz
		ContatoTest contato = new ContatoTest();
		contato.setId(Integer.parseInt(getChildTagValue(raiz, "id")));
		contato.setNome(getChildTagValue(raiz, "nome"));
		contato.setEmail(getChildTagValue(raiz, "email"));

		//Agora vamos inserir em contato os objetos telefones e endereco
		contato.setEndereco(endereco);
		contato.setTelefones(telefones);

		return contato;
		
	}
	
	
	
	public static void main(String[] args) {
		
		TelefoneTest residencial = new TelefoneTest();
		residencial.setId(1);
		residencial.setDdd(55);
		residencial.setNumero(32214512);

		TelefoneTest celular = new TelefoneTest();
		celular.setId(2);
		celular.setDdd(55);
		celular.setNumero(99879885);

		List<TelefoneTest> telefones = new ArrayList<TelefoneTest>();
		telefones.add(residencial);
		telefones.add(celular);

		EnderecoTest endereco = new EnderecoTest();
		endereco.setId(11);
		endereco.setLogradouro("Rua dos Javanezes");
		endereco.setBairro("Largo Zero");
		endereco.setCep("97010600");
		endereco.setCidade("Java City");
		endereco.setNumero(65);
		endereco.setComplemento("Ap.103A");

		ContatoTest contato = new ContatoTest();
		contato.setId(100);
		contato.setNome("Fulano da Silva");
		contato.setEmail("fulano@email.com");
		contato.setEndereco(endereco);
		contato.setTelefones(telefones);

		try {
			gerarXml(contato);
			//Contato c = lerXml();
			//System.out.println(c.toString());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
