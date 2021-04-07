package br.com.fattoria.sccm.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class DocumentStorageService {
	
	private Path fileStorageLocation;
	
	public DocumentStorageService() {		
		this.fileStorageLocation = Paths.get("C:\\Users\\Victor").toAbsolutePath().normalize();		
	}



	public Resource loadFileAsResource(String filename) throws Exception {
		
		try {
			Path filePath = this.fileStorageLocation.resolve(filename).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("Arquivo " + filename + " não encontrado!");
			}
		} catch (MalformedURLException e) {
			throw new FileNotFoundException("Arquivo " + filename + " não encontrado!");
		}		
	}
	
}
