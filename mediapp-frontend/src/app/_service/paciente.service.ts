import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Paciente } from '../_model/paciente';

@Injectable({
  providedIn: 'root'
})
export class PacienteService {

  url: string = `${environment.HOST}/pacientes`;

  constructor(private http : HttpClient) { }

  listar(){
    return this.http.get<Paciente[]>(this.url);
  }

  listarPorId(idPaciente : number){
    return this.http.get<Paciente[]>(`${this.url}/${idPaciente}`);
  }

  registrar (paciente : Paciente){
    return this.http.post(this.url, paciente);
  }

  modificar(paciente : Paciente){
    return this.http.put(this.url, paciente);
  }

  eliminar(idPaciente : number){
    return this.http.delete(`${this.url}/${idPaciente}`);
  }
}