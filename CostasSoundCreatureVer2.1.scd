
//===================================================================
//==============                 ====================================
//==============  Sound Creature ====================================
//==============                 ====================================
//===================================================================

// Start the server
Server.default.boot;

(  // Start command group
// Load buffer
~flute = Buffer.read(Server.default, "~/audiofiles/flute.wav".standardizePath);

// Define the function of breathing
~breathFunc = { |buf, freq = 220, mul = 0.5, rate = 1, attack = 0.2, decay = 0.4, sustain = 0.8, release = 1.0, gate = 1|

	var env,body,soul;

	env = EnvGen.kr(
		Env.adsr(attack, decay, sustain, release),
		gate: gate,
		doneAction: 2);
	body = PlayBuf.ar(buf.numChannels, buf, rate: rate, loop: 1).dup;
	soul = SinOsc.ar(freq, 0, mul).dup;
	(body * soul) * env;
};

// start breathing
~breathSynth = ~breathFunc.play;

~state=0; // default state (Normal)

{  // main routine
	while {~state != 10} {
		switch (~state,
		0, {
			"State 0 - Day Normal".postln;
			~breathSynth.set(
				\freq, 220,
			   \mul, 0.5,
				\rate, 1,
				\attack, 0.2,    // ?????????????
				\decay, 0.4,     // ????????????
				\sustain, 0.8,  // ?????????????
				\release, 1.0   // ????????????
			);
		},
		1, {
			"State 1 - Hunting".postln;
			~breathSynth.set(
				\freq, 660,
			   \mul, 1,
				\rate, 1.5
			);
		},
		2, {
			"State 2 - Hiding".postln;
		   ~breathSynth.set(
				\freq, 100,
			   \mul, 0.1,
				\rate, 0.3
			);
		},
		3, {
			"State 3 - Seeking other creatures".postln;
		   ~breathSynth.set(
				\freq, 440,
			   \mul, 1.5,
				\rate, 1.2
			);
		},
		4, {
			"State 4 - Night Normal".postln;
		   ~breathSynth.set(
				\freq, 330,
			   \mul, 0.8,
				\rate, 1
			);
		},
		5, {
			"State 5 - Sleeping".postln;
		   ~breathSynth.set(
				\freq, 100,
			   \mul, 0.4,
				\rate, 0.5
			);
		}
		);
		0.5.wait;
	};
	"Stop breathing...".postln;
	~breathSynth.set(\gate, 0);
}.fork;
)  // End command group

/*
   Creatures' States
*/
~state = 0; // 0 : Day - Normal
~state = 1; // 1 : Hunting
~state = 2; // 2 : Hiding
~state = 3; // 3 : Seeking other creatures
~state = 4; // 4 : Night - Normal
~state = 5; // 5 : Sleeping

~state = 10; // 10 : End

