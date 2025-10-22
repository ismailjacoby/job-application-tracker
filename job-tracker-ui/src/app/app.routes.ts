import { Routes } from '@angular/router';
import { jobRoutes } from './features/jobs/pages/job.routes';
import { DashboardLayout } from './features/layout/dashboard-layout/dashboard-layout';

export const routes: Routes = [
  {
    path: 'app',
    loadComponent: () =>
      import('./features/layout/dashboard-layout/dashboard-layout').then(
        (m) => m.DashboardLayout
      ),
    children: [
      {
        path: 'dashboard',
        loadComponent: () =>
          import('./features/dashboard/pages/dashboard/dashboard').then(
            (m) => m.Dashboard
          ),
      },
      {
        path: 'jobs',
        loadChildren: () =>
          import('./features/jobs/pages/job.routes').then(
            (m) => m.jobRoutes
          ),
      },
      {
        path: 'users',
        loadChildren: () =>
          import('./features/users/user.routes').then(
            (m) => m.userRoutes
          ),
      },
    ],
  },
];
