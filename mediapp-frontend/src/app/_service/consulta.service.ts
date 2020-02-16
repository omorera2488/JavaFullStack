import { Consulta } from './../_model/consulta';
import { ConsultaListaExamenDTO } from './../_dto/consultaListaExamenDTO';
import { FiltroConsultaDTO } from './../_dto/filtroConsultaDTO';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { ConsultaResumenDTO } from '../_dto/consultaResumenDTO';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  url: string = `${environment.HOST}/consultas`;
  
  constructor(private http: HttpClient) { }

  registrar(consultaDTO: ConsultaListaExamenDTO) {
    return this.http.post(`${this.url}/consultaExamenes`, consultaDTO);
  }

  buscar(filtroConsulta : FiltroConsultaDTO){
    return this.http.post<Consulta[]>(`${this.url}/buscar`, filtroConsulta);
  }

  listarResumen(){
    return this.http.get<ConsultaResumenDTO[]>(`${this.url}/listarResumen`);
  }

  generarReporte(){
    return this.http.get(`${this.url}/generarReporte`, {
      responseType: 'blob'
    });
  }

  listarExamenPorConsulta(idConsulta: number){
    return this.http.get<ConsultaListaExamenDTO[]>(`${environment.HOST}/consultaExamenes/${idConsulta}`);
  }

  guardarArchivo(data: File){
    let formdata: FormData = new FormData();
    formdata.append('adjunto', data);

    return this.http.post(`${this.url}/guardarArchivo`, formdata);
  }

  leerArchivo(){
    return this.http.get(`${this.url}/leerArchivo/1`, {
      responseType: 'blob'
    });
  }
}
