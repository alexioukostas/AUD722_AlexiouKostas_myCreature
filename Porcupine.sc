Porcupine : Creature {
    *fileName { ^"porcupine.wav" }

    *addSynthDefs {
        SynthDef(\porcupine, {

			arg buf, freq=220,
		       attack=0.2, decay=0.2, sustain=0.8, release=0.1,
			    out = 0, gate = 1,
			    rate=1,
			    lfoRate = 2, minMul = 0, maxMul = 0.8;

			var env, body, soul, finalSignal, lfoMul;

			lfoMul = SinOsc.kr(lfoRate).range(minMul, maxMul);

			env = EnvGen.kr(
				Env.adsr(attack, decay, sustain, release),
			   gate: gate,
				doneAction: 2
			);
			body = PlayBuf.ar(buf.numChannels, buf, rate: rate, loop: 1);
			soul = SinOsc.ar(freq, 0, lfoMul);

			finalSignal = (body * soul) * env;

			Out.ar(out, Pan2.ar(finalSignal, 0));

        }).add;
    }

   dawn {
		"--- Porcupine Dawn sound start ---".postln;
        this.substitute(
            Synth(\porcupine, [
                \buf, this.buffer,
                \freq, 660,
                \rate, 2,
                \lfoRate, 1.5,
                \minMul, 0.1,
                \maxMul, 0.7
            ])
        );
	}

	day {
        "--- Porcupine Day sound start ---".postln;
        this.substitute(
            Synth(\porcupine, [
                \buf, this.buffer,
                \freq, 440,
                \rate, 1.5,
                \lfoRate, 0.8,
                \minMul, 0.05,
                \maxMul, 1
            ])
        );
    }

	 dusk {
        "--- Porcupine Dusk sound start ---".postln;
        this.substitute(
            Synth(\porcupine, [
                \buf, this.buffer,
                \freq, 660,
                \rate, 1,
                \lfoRate, 0.5,
                \minMul, 0.03,
                \maxMul, 0.8
            ])
        );

	}


    night {
        "--- Porcupine Night sound start ---".postln;
      //  this.release(1);
		  this.substitute(
            Synth(\porcupine, [
                \buf, this.buffer,
                \freq, 880,
                \rate, 0.5,
                \lfoRate, 0.3,
                \minMul, 0,
                \maxMul, 0.4
            ])
        );
    }

	danger {
        "--- Porcupine Danger sound start ---".postln;
      //  this.release(1);
		  this.substitute(
            Synth(\porcupine, [
                \buf, this.buffer,
                \freq, 220,
                \rate, 2,
                \lfoRate, 2,
                \minMul, 0.4,
                \maxMul, 1
            ])
        );
    }

}