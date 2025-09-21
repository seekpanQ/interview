package com.student.score.controller;

import com.student.score.common.RestResult;
import com.student.score.dto.request.*;
import com.student.score.dto.response.ScoreRecordDTO;
import com.student.score.service.ScoreRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 成绩记录控制器
 * </p>
 *
 * @author seekpan
 */
@RestController
@RequestMapping("/scoreRecords")
@Slf4j
public class ScoreRecordController {

    @Autowired
    private ScoreRecordService scoreRecordService;

    /**
     * 新增成绩记录
     *
     * @param request 请求对象
     * @return 返回结果
     */
    @PostMapping
    public ResponseEntity<RestResult<ScoreRecordDTO>> addScoreRecord(@Validated @RequestBody ScoreRecordRequest request) {
        log.info("新增成绩记录请求: {}", request);
        ScoreRecordDTO result = scoreRecordService.addScoreRecord(request);
        return ResponseEntity.ok(RestResult.success(result));
    }

    /**
     * 修改成绩记录
     *
     * @param request 请求对象
     * @return 返回结果
     */
    @PutMapping
    public ResponseEntity<RestResult<ScoreRecordDTO>> updateScoreRecord(@Validated @RequestBody ScoreRecordUpdateRequest request) {
        log.info("修改成绩记录请求: {}", request);
        ScoreRecordDTO result = scoreRecordService.updateScoreRecord(request);
        return ResponseEntity.ok(RestResult.success(result));
    }

    /**
     * 删除成绩记录
     *
     * @param request 请求对象
     * @return 返回结果
     */
    @DeleteMapping
    public ResponseEntity<RestResult<Void>> deleteScoreRecord(@Validated @RequestBody ScoreRecordDeleteRequest request) {
        log.info("删除成绩记录请求: {}", request);
        scoreRecordService.deleteScoreRecord(request.getRecordId());
        return ResponseEntity.ok(RestResult.success(null));
    }

    /**
     * 查询成绩记录列表
     *
     * @param request 请求对象
     * @return 返回结果
     */
    @GetMapping
    public ResponseEntity<RestResult<List<ScoreRecordDTO>>> getScoreRecordList(@Validated @RequestBody ScoreRecordListRequest request) {
        log.info("查询成绩记录列表请求: {}", request);
        List<ScoreRecordDTO> result = scoreRecordService.getScoreRecordList(request);
        return ResponseEntity.ok(RestResult.success(result));
    }

    /**
     * 批量导入成绩数据
     *
     * @param request 请求对象
     * @return 返回结果
     */
    @PostMapping("/batchImport")
    public ResponseEntity<RestResult<List<String>>> batchImportScoreRecords(@Validated @RequestBody BatchImportScoreRequest request) {
        log.info("批量导入成绩数据请求: {}", request);
        List<String> validationFailures = scoreRecordService.batchImportScoreRecords(request.getScoreList());
        if (!validationFailures.isEmpty()) {
            return ResponseEntity.ok(RestResult.failure("000001", "部分数据校验失败", validationFailures));
        }
        return ResponseEntity.ok(RestResult.success(null));
    }
}