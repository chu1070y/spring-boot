package org.salem;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.salem.domain.PDS;
import org.salem.domain.PDSFile;
import org.salem.persistence.PDSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

//Commit을 안쓰면 rollback으로 처리되어서 테스트만 해보고 db에 적용이 되지 않는다.
@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class Test2 {
	
	@Setter(onMethod_ = @Autowired)
	private PDSRepository pdsRepository;
	
	@Test
	public void testInsert() {
		
		PDS vo = new PDS();
		vo.setTitle("Sample PDS");
		vo.setWriter("user00");
		
		List<PDSFile> list = IntStream.range(0,3).mapToObj(i -> {
			
			PDSFile fileObj = new PDSFile();
			fileObj.setFname("파일이름" + i);
			
			return fileObj;
		}).collect(Collectors.toList());
		
		vo.setFiles(list);
		
		log.info(""+ vo);
		
		pdsRepository.save(vo);
	}
	
	@Test
	public void testRead() {
		
		pdsRepository.findById(1L).ifPresent(vo -> log.info("" + vo));
		
	}
	
	//3번 수정
	//쿼리문이 하나뿐이지만 실제로 조회할때는 pds와 pdsfile 둘다 걸리기 때문에 transactional을 써야한다.
	@Transactional
	@Test
	public void testUpdateFile() {
		
		pdsRepository.updatePDSFiles(3L, "새로운 파일이름2");
	}
	
	@Transactional
	@Test
	public void testDeleteFile() {
		
		pdsRepository.deletePDSFile(3L);
	}
	
	//내가 필요한 순간에 쿼리를 또 날려야하기 때문에 Lazy 로딩은 항상 transactional이 들어간다.
	@Transactional
	//수정하는 것을 @query 말고 옛날방법으로 하기
	@Test
	public void testOldway() {
		
		//여기서 select문이 하나 날라가고
		PDS pds = pdsRepository.findById(1L).get();
		
		//여기서 select문이 또 날라간다. 그 이유는 Lazy loading때문이다. 필요한것만 갖고 오기 때문이다.
		List<PDSFile> files = pds.getFiles();
		
		log.info(""+files);
		
		PDSFile newFile = new PDSFile();
		newFile.setFno(2L);
		newFile.setFname("갱신된 파일");
		
		int idx = files.indexOf(newFile);
		
		log.info("idx: " + idx);
		
		if(idx >= 0) {
			files.remove(idx);
			files.add(newFile);
		}
		
		pds.setFiles(files);
		
		pdsRepository.save(pds);
		
	}
	
	
	
}
