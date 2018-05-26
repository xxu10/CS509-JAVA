package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utils.CompareTime;
import departing.Departing;
import transfer.Transfer1;
import transfer.Transfer2;
import utils.CompareLocaltime;

public class Sort_by {
    //CompareTime comparetime = new CompareTime();
    CompareLocaltime comparetime = new CompareLocaltime();
	public ArrayList<Departing> Sort_by_no(int sort_choice, ArrayList<Departing> flights,int upward){
		if (sort_choice == 1) {
			Collections.sort(flights, new Comparator<Departing>() {
				public int compare(Departing departing1, Departing departing2) {
					String ddate1 = departing1.ddate();
					String ddate2 = departing2.ddate();
					int time_length = comparetime.Compare_time(ddate1, ddate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 2) {
			Collections.sort(flights, new Comparator<Departing>() {
				public int compare(Departing departing1, Departing departing2) {
					String adate1 = departing1.adate();
					String adate2 = departing2.adate();
					int time_length = comparetime.Compare_time(adate1, adate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 3) {
			Collections.sort(flights, new Comparator<Departing>() {
				public int compare(Departing departing1, Departing departing2) {
					String firstclass1 = departing1.firstclass().substring(1);
					String firstclass2 = departing2.firstclass().substring(1);
					float first_price1 = Float.parseFloat(firstclass1);
					float first_price2 = Float.parseFloat(firstclass2);
					if (upward == 1) {
						if ((first_price1 - first_price2) >= 0)
							return 1;
						else
							return -1;
					} else {
						if ((first_price1 - first_price2) >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 4) {
			Collections.sort(flights, new Comparator<Departing>() {
				public int compare(Departing departing1, Departing departing2) {
					String coach1 = departing1.coach().substring(1);
					String coach2 = departing2.coach().substring(1);
					float coach_price1 = Float.parseFloat(coach1);
					float coach_price2 = Float.parseFloat(coach2);
					if (upward == 1) {
						if ((coach_price1 - coach_price2) >= 0)
							return 1;
						else
							return -1;
					} else {
						if ((coach_price1 - coach_price2) >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
	   }
		if(sort_choice == 5){
			 Collections.sort(flights, new Comparator<Departing>() {
					public int compare(Departing departing1, Departing departing2) {
						String adate1 = departing1.adate();
						String adate2 = departing2.adate();
						String ddate1 = departing1.ddate();
						String ddate2 = departing2.ddate();
						int time_length1 = comparetime.Compare_time(ddate1, adate1);
						int time_length2 = comparetime.Compare_time(ddate2, adate2);
						if (upward == 1) {
							if (time_length1 >= time_length2)
								return 1;
							else
								return -1;
						} else {
							if (time_length1 >= time_length2)
								return -1;
							else
								return 1;
						}

					}
				});
		}
	   else 
		   Collections.sort(flights, new Comparator<Departing>() {
				public int compare(Departing departing1, Departing departing2) {
					String adate1 = departing1.adate();
					String adate2 = departing2.adate();
					int time_length = comparetime.Compare_time(adate1, adate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
		return flights;
	}
	
	public ArrayList<Transfer1> Sort_by_onetransfer(int sort_choice, ArrayList<Transfer1> flights,int upward){
		if (sort_choice == 1) {
			Collections.sort(flights, new Comparator<Transfer1>() {
				public int compare(Transfer1 transfer1, Transfer1 transfer2) {
					String ddate1 = transfer1.ddate1();
					String ddate2 = transfer2.ddate1();
					int time_length = comparetime.Compare_time(ddate1, ddate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 2) {
			Collections.sort(flights, new Comparator<Transfer1>() {
				public int compare(Transfer1 transfer1, Transfer1 transfer2) {
					String adate1 = transfer1.adate1();
					String adate2 = transfer2.adate1();
					int time_length = comparetime.Compare_time(adate1, adate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 3) {
			Collections.sort(flights, new Comparator<Transfer1>() {
				public int compare(Transfer1 transfer1, Transfer1 transfer2) {
					String firstclass11 = transfer1.firstclass1().substring(1);
					String firstclass12 = transfer1.firstclass2().substring(1);
					String firstclass21 = transfer2.firstclass1().substring(1);
					String firstclass22 = transfer2.firstclass2().substring(1);
					float first_price11 = Float.parseFloat(firstclass11);
					float first_price12 = Float.parseFloat(firstclass12);
					float first_price21 = Float.parseFloat(firstclass21);
					float first_price22 = Float.parseFloat(firstclass22);
					float f1 = (first_price11 + first_price12) / 2;
					float f2 = (first_price21 + first_price22) / 2;
					if (upward == 1) {
						if ((f1 - f2) >= 0)
							return 1;
						else
							return -1;
					} else {
						if ((f1 - f2) >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 4) {
			Collections.sort(flights, new Comparator<Transfer1>() {
				public int compare(Transfer1 transfer1, Transfer1 transfer2) {
					String coach11 = transfer1.coach1().substring(1);
					String coach12 = transfer1.coach2().substring(1);
					String coach21 = transfer2.coach1().substring(1);
					String coach22 = transfer2.coach2().substring(1);
					float coach_price11 = Float.parseFloat(coach11);
					float coach_price12 = Float.parseFloat(coach12);
					float coach_price21 = Float.parseFloat(coach21);
					float coach_price22 = Float.parseFloat(coach22);
					float c1 = (coach_price11 + coach_price12) / 2;
					float c2 = (coach_price21 + coach_price22) / 2;
					if (upward == 1) {
						if ((c1 - c2) >= 0)
							return 1;
						else
							return -1;
					} else {
						if ((c1 - c2) >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		} 
		if(sort_choice == 5){
			 Collections.sort(flights, new Comparator<Transfer1>() {
					public int compare(Transfer1 departing1, Transfer1 departing2) {
						String adate1 = departing1.adate2();
						String adate2 = departing2.adate2();
						String ddate1 = departing1.ddate1();
						String ddate2 = departing2.ddate1();
						int time_length1 = comparetime.Compare_time(ddate1, adate1);
						int time_length2 = comparetime.Compare_time(ddate2, adate2);
						if (upward == 1) {
							if (time_length1 >= time_length2)
								return 1;
							else
								return -1;
						} else {
							if (time_length1 >= time_length2)
								return -1;
							else
								return 1;
						}

					}
				});
		}	
		else
			Collections.sort(flights, new Comparator<Transfer1>() {
				public int compare(Transfer1 transfer1, Transfer1 transfer2) {
					String adate1 = transfer1.adate1();
					String adate2 = transfer2.adate1();
					int time_length = comparetime.Compare_time(adate1, adate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
		return flights;
	}
	
	public ArrayList<Transfer2> Sort_by_twotransfer(int sort_choice, ArrayList<Transfer2> flights, int upward){
		if (sort_choice == 1) {
			Collections.sort(flights, new Comparator<Transfer2>() {
				public int compare(Transfer2 transfer1, Transfer2 transfer2) {
					String ddate1 = transfer1.ddate1();
					String ddate2 = transfer2.ddate1();
					int time_length = comparetime.Compare_time(ddate1, ddate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 2) {
			Collections.sort(flights, new Comparator<Transfer2>() {
				public int compare(Transfer2 transfer1, Transfer2 transfer2) {
					String adate1 = transfer1.adate1();
					String adate2 = transfer2.adate1();
					int time_length = comparetime.Compare_time(adate1, adate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 3) {
			Collections.sort(flights, new Comparator<Transfer2>() {
				public int compare(Transfer2 transfer1, Transfer2 transfer2) {
					String firstclass11 = transfer1.firstclass1().substring(1);
					String firstclass12 = transfer1.firstclass2().substring(1);
					String firstclass21 = transfer2.firstclass1().substring(1);
					String firstclass22 = transfer2.firstclass2().substring(1);
					String firstclass13 = transfer1.firstclass3().substring(1);
					String firstclass23 = transfer2.firstclass3().substring(1);
					float first_price11 = Float.parseFloat(firstclass11);
					float first_price12 = Float.parseFloat(firstclass12);
					float first_price21 = Float.parseFloat(firstclass21);
					float first_price22 = Float.parseFloat(firstclass22);
					float first_price13 = Float.parseFloat(firstclass13);
					float first_price23 = Float.parseFloat(firstclass23);
					float f1 = (first_price11 + first_price12 + first_price13) / 3;
					float f2 = (first_price21 + first_price22 + first_price23) / 3;
					if (upward == 1) {
						if ((f1 - f2) >= 0)
							return 1;
						else
							return -1;
					} else {
						if ((f1 - f2) >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		}
		if (sort_choice == 4) {
			Collections.sort(flights, new Comparator<Transfer2>() {
				public int compare(Transfer2 transfer1, Transfer2 transfer2) {
					String coach11 = transfer1.coach1().substring(1);
					String coach12 = transfer1.coach2().substring(1);
					String coach13 = transfer1.coach3().substring(1);
					String coach21 = transfer2.coach1().substring(1);
					String coach22 = transfer2.coach2().substring(1);
					String coach23 = transfer2.coach3().substring(1);
					float coach_price11 = Float.parseFloat(coach11);
					float coach_price12 = Float.parseFloat(coach12);
					float coach_price21 = Float.parseFloat(coach21);
					float coach_price22 = Float.parseFloat(coach22);
					float coach_price13 = Float.parseFloat(coach13);
					float coach_price23 = Float.parseFloat(coach23);
					float c1 = (coach_price11 + coach_price12 + coach_price13) / 3;
					float c2 = (coach_price21 + coach_price22 + coach_price23) / 3;
					if (upward == 1) {
						if ((c1 - c2) >= 0)
							return 1;
						else
							return -1;
					} else {
						if ((c1 - c2) >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
			return flights;
		} 
		if(sort_choice == 5){
			 Collections.sort(flights, new Comparator<Transfer2>() {
					public int compare(Transfer2 departing1, Transfer2 departing2) {
						String adate1 = departing1.adate3();
						String adate2 = departing2.adate3();
						String ddate1 = departing1.ddate1();
						String ddate2 = departing2.ddate1();
						int time_length1 = comparetime.Compare_time(ddate1, adate1);
						int time_length2 = comparetime.Compare_time(ddate2, adate2);
						if (upward == 1) {
							if (time_length1 >= time_length2)
								return 1;
							else
								return -1;
						} else {
							if (time_length1 >= time_length2)
								return -1;
							else
								return 1;
						}

					}
				});
		}	
		else
			Collections.sort(flights, new Comparator<Transfer2>() {
				public int compare(Transfer2 transfer1, Transfer2 transfer2) {
					String adate1 = transfer1.adate1();
					String adate2 = transfer2.adate1();
					int time_length = comparetime.Compare_time(adate1, adate2);
					if (upward == 1) {
						if (time_length >= 0)
							return 1;
						else
							return -1;
					} else {
						if (time_length >= 0)
							return -1;
						else
							return 1;
					}

				}
			});
		return flights;
	}

}
