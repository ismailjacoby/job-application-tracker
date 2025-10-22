
import {JobSource} from '../../../features/jobs/models/job-source.enum';
import {ApplicationStatus} from '../../../features/jobs/models/application-status.enum';


export interface Job {
  title: string;
  companyName: string;
  location: string;
  jobUrl: string;
  jobSource: JobSource;
  status: ApplicationStatus;
  dateApplied?: string;
  recruiterName?: string;
  recruiterEmail?: string;
  recruiterPhone?: string;
  salary?: string;
  notes?: string;
}

export interface JobShort {
  id: number;
  title: string;
  companyName: string;
  location: string;
  jobUrl: string;
  jobSource: JobSource;
  status: ApplicationStatus;
  dateApplied?: string;
  salary?: string;
}
