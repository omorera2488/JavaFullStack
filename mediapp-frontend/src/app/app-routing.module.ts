import { Not404Component } from './pages/not404/not404.component';
import { Not403Component } from './pages/not403/not403.component';
import { GuardService } from './_service/guard.service';
import { LoginComponent } from './pages/login/login.component';
import { ReporteComponent } from './pages/reporte/reporte.component';
import { BuscarComponent } from './pages/buscar/buscar.component';
import { WizardComponent } from './pages/consulta/wizard/wizard.component';
import { ExamenEdicionComponent } from './pages/examen/examen-edicion/examen-edicion.component';
import { ExamenComponent } from './pages/examen/examen.component';
import { MedicoComponent } from './pages/medico/medico.component';
import { PacienteComponent } from './pages/paciente/paciente.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PacienteEdicionComponent } from './pages/paciente/paciente-edicion/paciente-edicion.component';
import { EspecialidadComponent } from './pages/especialidad/especialidad.component';
import { EspecialidadEdicionComponent } from './pages/especialidad/especialidad-edicion/especialidad-edicion.component';
import { EspecialComponent } from './pages/consulta/especial/especial.component';
import { ConsultaComponent } from './pages/consulta/consulta.component';

const routes: Routes = [
  {path:"paciente", component:PacienteComponent, children:[
    {path:"nuevo", component:PacienteEdicionComponent},
    {path:"edicion/:id", component:PacienteEdicionComponent}
  ]},
  {
    path:"medico", component:MedicoComponent
  },
  {path:"examen", component:ExamenComponent, children:[
    {path:"nuevo", component:ExamenEdicionComponent},
    {path:"edicion/:id", component:ExamenEdicionComponent}
  ]},
  {path:"especialidad", component:EspecialidadComponent, children:[
    {path:"nuevo", component:EspecialidadEdicionComponent},
    {path:"edicion/:id", component:EspecialidadEdicionComponent}
  ]},
  { path: 'medico', component: MedicoComponent },
  { path: 'consulta', component: ConsultaComponent },
  { path: 'consulta-especial', component: EspecialComponent},
  { path: 'consulta-wizard', component: WizardComponent},
  { path: 'buscar', component: BuscarComponent},
  { path: 'reporte', component: ReporteComponent},
  { path: 'not-403', component: Not403Component },
  { path: 'not-404', component: Not404Component },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'not-404', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
