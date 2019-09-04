package kr.co.MIND.schedule;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import kr.co.MIND.shareList.ShareListDAO;
import kr.co.MIND.shareList.ShareListDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ScheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	@Inject
	ScheduleDAO scheduleDao;

	@Inject
	ShareListDAO sharelistDao;

	@Override
	public List showAllSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub

		return scheduleDao.showAllSchedule(dto);
	}

	@Override
	public void createSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		scheduleDao.createSchedule(dto);

	}

	@Override
	public void updateSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		scheduleDao.updateSchedule(dto);

	}

	@Override
	public void deleteSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		scheduleDao.deleteSchedule(dto);
	}

	@Override
	public ScheduleDTO showSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		return scheduleDao.showSchedule(dto);
	}

	@Override
	public List searchSchedule(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		return scheduleDao.searchSchedule(dto);
	}

	@Override
	public void updateNumOfComments(ScheduleDTO dto) {
		// TODO Auto-generated method stub
		scheduleDao.updateNumOfComments(dto);
	}

	public String changeDate(String date) {
		SimpleDateFormat original_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat new_format = new SimpleDateFormat("yyyyMMddHHmm");

		try {
			Date original_date = original_format.parse(date);

			String new_date = new_format.format(original_date);
			new_date = new_date.substring(2, 12);
			return new_date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<String> recommendSche(ScheduleDTO dto) throws ParseException {

		boolean[][] rCal = new boolean[24][6];
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 6; j++) {
				rCal[i][j] = true;
			}
		}
		String date = dto.getStartDate();

		String new_date = changeDate(date);

		int start = Integer.parseInt(new_date);
		start = start / 10000;

		List<String> result = new ArrayList<>();
		List<String> uid = sharelistDao.recommendSche01(dto);
		for (String object : uid) {
			List<Integer> cid = sharelistDao.recommendSche02(object);
			for (Integer object2 : cid) {
				List<ScheduleDTO> sche = scheduleDao.recommendSche03(object2);
				for (ScheduleDTO object3 : sche) {
					// 월 일이 같은 경우
					String ndate = changeDate(object3.getStartDate());
					int check = Integer.parseInt(ndate);
					int sameDay = check / 10000;

					String ndate2 = changeDate(object3.getEndDate());
					int checkTime = Integer.parseInt(ndate2);
					int endTime = (checkTime % 10000);
					if (start == sameDay) {
//						19 08 25 05 16  - 19 08 27 07 16 
						check = check % 10000; // 05 16
						int hour = check / 100; // 5
						int minute = ((check % 100) / 10); // 16 -> 1
						if (minute == -1) {
							hour -= 1;
							minute = 5;
						}
						// 07 16
						int endHour = endTime / 100; // 7
						int endMin = ((endTime % 100) / 10); // 2
						if (endMin == 6) {
							endHour += 1;
							endMin = 0;
						}
//						5,1 ~ 7,2
						for (int i = hour; i <= endHour; i++) {
							for (int j = 0; j < 6; j++) {
								if (i == hour && j == 0 && minute != 0) {
									j = minute;
									continue;
								}
								if (i == endHour && j > endMin) {
									break;
								}
								rCal[i][j] = false;

							}
						}
					}
				}
			}
		}
	
		int startDate = -1;
		int endDate = 0;
		int count = 0;
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 6; j++) {

				if (rCal[i][j] == true) {
					count++;

					if (startDate == 0 ) {
						startDate = i * 10 + j;
					}
				}
				if (rCal[i][j] == false || (i == 23 && j == 5)) {
					if (count >= 6) {
						endDate = i * 10 + j;
						if(startDate == -1) {
							startDate = 0;
						}
						if(endDate == 235) {
							endDate = 0;
						}
						String sdate = Integer.toString(startDate / 10) + ":" + Integer.toString(startDate % 10) + "0";
						String edate = Integer.toString(endDate / 10) + ":" + Integer.toString(endDate % 10) + "0";
						result.add(sdate + " ~ " + edate);
					}
					startDate = 0;
					count = 0;
				}
			}
		}
		return result;
	}
}
