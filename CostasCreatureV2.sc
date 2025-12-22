CostasCreature2 {
	var <server, <buffer, <routine;
	var freq, mul, rate, attack, decay, sustain, release, triggerRate;

	*new { |server, buffer|
		^super.new.init(server, buffer);
	}
//---- Define the behavor
	init {
		freq = 220;
		mul = 0.5;
		rate = 1;
		attack = 0.2;
		decay = 0.4;
		sustain = 0.8;
		release = 1.0;
		triggerRate = 0.5;

		SynthDef(\breath, {
			arg buf, freq, mul, rate,
		   attack, decay, sustain, release,
			out = 0;

			var env, body, soul, finalSignal;

			env = EnvGen.kr(
				Env.adsr(attack, decay, sustain, release),
			   gate: Line.kr(1, 0, attack + decay + 0.1),
				doneAction: 2
			);
			body = PlayBuf.ar(buf.numChannels, buf, rate: rate, loop: 1);
			soul = SinOsc.ar(freq, 0, mul);

			finalSignal = (body * soul) * env;

			Out.ar(out, Pan2.ar(finalSignal, 0));
		}).add;
	}
// ---- Start breathing
	play {
		if(routine.isNil or: { routine.isPlaying.not }){
			routine = Routine ({
				loop {
					Synth(\breath, [
						\buf, buffer,
						\freq, freq,
						\mul, mul,
						\rate, rate,
						\attack, attack,
						\decay, decay,
						\sustain, sustain,
						\release, release
					], server);

					(1 / triggerRate).wait;
				}
			}).play;
		}
	}

// ---- Methods for each creature's state

	day {
		freq = 220;
		mul = 0.5;
		rate = 1;
		attack = 0.2;
		decay = 0.4;
		sustain = 0.8;
		release = 1.0;
		triggerRate = 0.5;
	}

	hunt {
		freq = 880;
		mul = 0.5;
		rate = 1;
		attack = 0.1;
		decay = 0.2;
		sustain = 1;
		release = 0.5;
		triggerRate = 2;
	}

	hide {
		freq = 100;
		mul = 0.1;
		rate = 1;
		attack = 0.4;
		decay = 0.6;
		sustain = 0.5;
		release = 2;
		triggerRate = 0.3;
	}

	seek {
		freq = 220;
		mul = 0.5;
		rate = 1;
		attack = 0.2;
		decay = 0.4;
		sustain = 0.8;
		release = 1.0;
		triggerRate = 0.5;
	}

	night {
		freq = 220;
		mul = 0.5;
		rate = 1;
		attack = 0.2;
		decay = 0.4;
		sustain = 0.8;
		release = 1.0;
		triggerRate = 0.5;
	}

	sleep {
		freq = 220;
		mul = 0.5;
		rate = 1;
		attack = 0.2;
		decay = 0.4;
		sustain = 0.8;
		release = 1.0;
		triggerRate = 0.5;
	}

// ---- Stop breathing
	stop {
		if (routine.notNil) { routine.stop };
	}
}