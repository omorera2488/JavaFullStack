import { Component, OnInit, ViewChild } from '@angular/core';
import { PacienteService } from 'src/app/_service/paciente.service';
import { MatTableDataSource, MatSort, MatPaginator, MatSnackBar } from '@angular/material';
import { Paciente } from 'src/app/_model/paciente';

@Component({
  selector: 'app-paciente',
  templateUrl: './paciente.component.html',
  styleUrls: ['./paciente.component.css']
})
export class PacienteComponent implements OnInit {

  displayedColumns = ['idPaciente', 'nombres', 'apellidos', 'acciones'];
  dataSource: MatTableDataSource<Paciente>;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    private pacienteService: PacienteService,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit() {
    this.pacienteService.mensajeCambio.subscribe(data => {
      this.snackBar.open(data, 'Mensaje', {duration:2000});
    });

    this.pacienteService.pacienteCambio.subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });

    this.pacienteService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });
  }

  filtrar(valor: string) {
    this.dataSource.filter = valor.trim().toLowerCase();
  }

  eliminar(id: number) {
    this.pacienteService.eliminar(id).subscribe(() =>{
      this.pacienteService.listar().subscribe(data =>{
        this.pacienteService.pacienteCambio.next(data);
        this.pacienteService.mensajeCambio.next("Paciente eliminado");
      });
    });
  }

}
