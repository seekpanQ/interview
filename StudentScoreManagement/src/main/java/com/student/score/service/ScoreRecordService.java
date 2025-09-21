package com.student.score.service;

import com.student.score.dto.request.ScoreRecordListRequest;
import com.student.score.dto.request.ScoreRecordRequest;
import com.student.score.dto.request.ScoreRecordUpdateRequest;
import com.student.score.dto.response.ScoreRecordDTO;
import java.util.List;

/**
 * <p>
 *   成绩记录服务接口
 * </p>
 * @author seekpan
 */
public interface ScoreRecordService {

    ScoreRecordDTO addScoreRecord(ScoreRecordRequest request);
    ScoreRecordDTO updateScoreRecord(ScoreRecordUpdateRequest request);
    void deleteScoreRecord(Integer recordId);
    List<ScoreRecordDTO> getScoreRecordList(ScoreRecordListRequest request);
    List<String> batchImportScoreRecords(List<ScoreRecordRequest> scoreList);
}