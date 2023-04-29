package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Diary Controller", description = "다이어리 컨트롤러")
public class DiaryController {
	private final DiaryService diaryService;

	public DiaryController(DiaryService diaryService) {
		this.diaryService = diaryService;
	}

	@Operation(summary = "일기 작성", description = "날짜와 텍스트를 이용해서 DB 에 일기 저장")
	@PostMapping("/create/diary")
	void createDiary(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@Parameter(description = "작성 날짜", example = "2020-01-01") LocalDate date
			, @Parameter(description = "작성 내용", example = "Hello World") @RequestBody String text
	) {
		diaryService.createDiary(date, text);

	}

	@Operation(summary = "일기 조회", description = "선택한 날짜 모든 일기 조회")
	@GetMapping("/read/diary")
	List<Diary> readDiary(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@Parameter(description = "조회 날짜", example = "2020-01-01") LocalDate date
	) {
		return diaryService.readDiary(date);
	}

	@Operation(summary = " 기간 일기 조회", description = "선택한 기간 모든 일기 조회", hidden = false)
	@GetMapping("/read/diaries")
	List<Diary> readDiaries(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@Parameter(description = "시작 날짜", example = "2020-01-01") LocalDate startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@Parameter(description = "끝 날짜", example = "2020-01-02") LocalDate endDate
	) {
		return diaryService.readDiaries(startDate, endDate);
	}

	@Operation(summary = "일기 수정", description = "선택한 날짜의 텍스트 수정 후 DB에 저장")
	@PutMapping("/update/diary")
	void updateDiary(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@Parameter(description = "수정할 날짜", example = "2020-01-01") LocalDate date
			, @Parameter(description = "수정할 내용", example = "fixed") @RequestBody String text
	) {
		diaryService.updateDiary(date, text);
	}

	@Operation(summary = "일기 삭제", description = "선택한 날짜 첫 일기 삭제")
	@DeleteMapping("/delete/diary")
	void deleteDiary(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
			@Parameter(description = "삭제 날짜", example = "2020-01-01") LocalDate date
	) {
		diaryService.deleteDiary(date);
	}

}
