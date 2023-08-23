import { Injectable } from '@angular/core';
import { RxStomp } from '@stomp/rx-stomp';
import { IMessage } from '@stomp/stompjs';
import { Observable } from 'rxjs';
import { RxStompService } from '../_services/rx-stomp.service';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  constructor(private rxStompService: RxStompService) {
  }

  sendMessage(projectionId: string, seatId: number): void {
    const destination = `/app/sendMessage/${projectionId}`;
    this.rxStompService.publish({ destination: destination, body: JSON.stringify({ seatId: seatId }) });
  }

  getMessages(projectionId: string): Observable<IMessage> {
    // Pretplata na kanal za primanje poruka
    return this.rxStompService.watch(`/topic/${projectionId}`);
  }

}
